package com.webfruit.model;
import com.webfruit.dao.Product;

import java.sql.*;
import java.util.ArrayList;
import com.webfruit.model.DBUtil;

public class HandleCRUDProduct {
    private Connection connection;

    public HandleCRUDProduct() throws SQLException {
        connection = DBUtil.getConnection();
    }

    public static HandleCRUDProduct getInstance() throws SQLException {
        return new HandleCRUDProduct();
    }

    // close connection
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // createProduct
    public boolean createProduct(Product product) {
        try {
            // Tạo câu lệnh SQL để chèn sản phẩm
            String query = "INSERT INTO san_pham (ten_san_pham, gia_san_pham, mo_ta_san_pham, ma_giam_gia, so_luong_san_pham, hinh_anh_san_pham) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // Thiết lập giá trị cho các tham số
            ps.setString(1, product.getTen_san_pham());
            ps.setDouble(2, product.getGia_san_pham());
            ps.setString(3, product.getMo_ta_san_pham());
            ps.setString(4, product.getMa_giam_gia());
            ps.setInt(5, Integer.parseInt(product.getSo_luong_san_pham()));
            ps.setString(6, product.getHinh_anh_san_pham());

            // Thực thi câu lệnh và lấy ID sản phẩm vừa tạo
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                // Lấy ID sản phẩm vừa tạo
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long newProductId = generatedKeys.getLong(1);
                    // Them du lieu vao san_pham_and_loai_san_pham
                    String query2 = "INSERT INTO san_pham_and_loai_san_pham (ID_san_pham, ID_loai_san_pham) VALUES (?, ?)";
                    PreparedStatement ps2 = connection.prepareStatement(query2);
                    ps2.setLong(1, newProductId);
                    ps2.setInt(2, product.getID_loai_san_pham());
                    int affectedRows2 = ps2.executeUpdate();
                    if (affectedRows2 > 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    // updateProduct
    public boolean updateProduct(Product product) {
        try {
            // Tạo câu lệnh SQL để cập nhật sản phẩm
            String query = "UPDATE san_pham SET ten_san_pham = ?, gia_san_pham = ?, mo_ta_san_pham = ?, ma_giam_gia = ?, so_luong_san_pham = ?, hinh_anh_san_pham = ? WHERE ID = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            System.out.println("Check SLSP: " + product.getSo_luong_san_pham());
            // Thiết lập giá trị cho các tham số
            ps.setString(1, product.getTen_san_pham());
            ps.setDouble(2, product.getGia_san_pham());
            ps.setString(3, product.getMo_ta_san_pham());
            ps.setString(4, product.getMa_giam_gia());
            ps.setInt(5, Integer.parseInt(product.getSo_luong_san_pham()));
            ps.setString(6, product.getHinh_anh_san_pham());
            ps.setLong(7, product.getID());

            // Thực thi câu lệnh
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                // cap nhat lai san_pham_and_loai_san_pham
                String query2 = "UPDATE san_pham_and_loai_san_pham SET ID_loai_san_pham = ? WHERE ID_san_pham = ?";
                PreparedStatement ps2 = connection.prepareStatement(query2);
                ps2.setInt(1, product.getID_loai_san_pham());
                ps2.setLong(2, product.getID());
                int affectedRows2 = ps2.executeUpdate();
                if (affectedRows2 > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    // deleteProduct
    public boolean deleteProduct(long ID) {
        try {
            // delete san_pham_and_loai_san_pham
            String query2 = "DELETE FROM san_pham_and_loai_san_pham WHERE ID_san_pham = ?";
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ps2.setLong(1, ID);
            int affectedRows2 = ps2.executeUpdate();
            if (affectedRows2 > 0) {
                // delete san_pham
                String query = "DELETE FROM san_pham WHERE ID = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setLong(1, ID);
                int affectedRows = ps.executeUpdate();
                if (affectedRows > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // select all products
    public ArrayList<Product> selectAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            // Tạo câu lệnh SQL để lấy tất cả sản phẩm
            String query = "SELECT san_pham.ID, san_pham.ten_san_pham, san_pham.gia_san_pham, san_pham.mo_ta_san_pham, san_pham.ma_giam_gia, san_pham.so_luong_san_pham, san_pham.hinh_anh_san_pham, loai_san_pham.id as ID_loai_san_pham, loai_san_pham.ten_loai_san_pham, san_pham.ngay_them, san_pham.ngay_cap_nhat FROM san_pham inner join san_pham_and_loai_san_pham on san_pham.ID = san_pham_and_loai_san_pham.ID_san_pham INNER JOIN loai_san_pham on loai_san_pham.id = san_pham_and_loai_san_pham.ID_loai_san_pham";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setID(rs.getInt("ID"));
                product.setTen_san_pham(rs.getString("ten_san_pham"));
                product.setGia_san_pham(rs.getDouble("gia_san_pham"));
                product.setMo_ta_san_pham(rs.getString("mo_ta_san_pham"));
                product.setMa_giam_gia(rs.getString("ma_giam_gia"));
                product.setSo_luong_san_pham(rs.getString("so_luong_san_pham"));
                product.setHinh_anh_san_pham(rs.getString("hinh_anh_san_pham"));
                product.setNgay_them(rs.getString("ngay_them"));
                product.setNgay_cap_nhat(rs.getString("ngay_cap_nhat"));
                product.setTen_loai_san_pham(rs.getString("ten_loai_san_pham"));
                product.setID_loai_san_pham(rs.getInt("ID_loai_san_pham"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    // select all loai_san_pham
    public ArrayList<Product> selectAllLoaiSanPham() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            // Tạo câu lệnh SQL để lấy tất cả loại sản phẩm
            String query = "SELECT * FROM loai_san_pham";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setID_loai_san_pham(rs.getInt("id"));
                product.setTen_loai_san_pham(rs.getString("ten_loai_san_pham"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public boolean createTypeProduct(String ten_loai_san_pham) {
        try {
            // Tạo câu lệnh SQL để chèn loại sản phẩm
            String query = "INSERT INTO loai_san_pham (ten_loai_san_pham) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);

            // Thiết lập giá trị cho các tham số
            ps.setString(1, ten_loai_san_pham);

            // Thực thi câu lệnh
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    // select all products by type product
    public ArrayList<Product> selectAllProductsByTypeProduct(int ID_loai_san_pham) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            // Tạo câu lệnh SQL để lấy tất cả sản phẩm theo loại sản phẩm
            String query = "SELECT san_pham.ID, san_pham.ten_san_pham, san_pham.gia_san_pham, san_pham.mo_ta_san_pham, san_pham.ma_giam_gia, san_pham.so_luong_san_pham, san_pham.hinh_anh_san_pham, loai_san_pham.id as ID_loai_san_pham, loai_san_pham.ten_loai_san_pham, san_pham.ngay_them, san_pham.ngay_cap_nhat FROM san_pham inner join san_pham_and_loai_san_pham on san_pham.ID = san_pham_and_loai_san_pham.ID_san_pham INNER JOIN loai_san_pham on loai_san_pham.id = san_pham_and_loai_san_pham.ID_loai_san_pham WHERE loai_san_pham.id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, ID_loai_san_pham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setID(rs.getInt("ID"));
                product.setTen_san_pham(rs.getString("ten_san_pham"));
                product.setGia_san_pham(rs.getDouble("gia_san_pham"));
                product.setMo_ta_san_pham(rs.getString("mo_ta_san_pham"));
                product.setMa_giam_gia(rs.getString("ma_giam_gia"));
                product.setSo_luong_san_pham(rs.getString("so_luong_san_pham"));
                product.setHinh_anh_san_pham(rs.getString("hinh_anh_san_pham"));
                product.setNgay_them(rs.getString("ngay_them"));
                product.setNgay_cap_nhat(rs.getString("ngay_cap_nhat"));
                product.setTen_loai_san_pham(rs.getString("ten_loai_san_pham"));
                product.setID_loai_san_pham(rs.getInt("ID_loai_san_pham"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public ArrayList<Product> selectAllProductsByNameEquasVegetables() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            // Tạo câu lệnh SQL để lấy tất cả sản phẩm theo loại sản phẩm
            String query = "SELECT san_pham.ID, san_pham.ten_san_pham, san_pham.gia_san_pham, san_pham.mo_ta_san_pham, san_pham.ma_giam_gia, san_pham.so_luong_san_pham, san_pham.hinh_anh_san_pham, loai_san_pham.id as ID_loai_san_pham, loai_san_pham.ten_loai_san_pham, san_pham.ngay_them, san_pham.ngay_cap_nhat FROM san_pham inner join san_pham_and_loai_san_pham on san_pham.ID = san_pham_and_loai_san_pham.ID_san_pham INNER JOIN loai_san_pham on loai_san_pham.id = san_pham_and_loai_san_pham.ID_loai_san_pham WHERE loai_san_pham.ten_loai_san_pham = 'Vegetables'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Product product = new Product();
                product.setID(rs.getInt("ID"));
                product.setTen_san_pham(rs.getString("ten_san_pham"));
                product.setGia_san_pham(rs.getDouble("gia_san_pham"));
                product.setMo_ta_san_pham(rs.getString("mo_ta_san_pham"));
                product.setMa_giam_gia(rs.getString("ma_giam_gia"));
                product.setSo_luong_san_pham(rs.getString("so_luong_san_pham"));
                product.setHinh_anh_san_pham(rs.getString("hinh_anh_san_pham"));
                product.setNgay_them(rs.getString("ngay_them"));
                product.setNgay_cap_nhat(rs.getString("ngay_cap_nhat"));
                product.setTen_loai_san_pham(rs.getString("ten_loai_san_pham"));
                product.setID_loai_san_pham(rs.getInt("ID_loai_san_pham"));
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    // select product by ID
    public Product selectProductByID(int ID) {
        Product product = new Product();
        try {
            // Tạo câu lệnh SQL để lấy sản phẩm theo ID
            String query = "SELECT san_pham.ID, san_pham.ten_san_pham, san_pham.gia_san_pham, san_pham.mo_ta_san_pham, san_pham.ma_giam_gia, san_pham.so_luong_san_pham, san_pham.hinh_anh_san_pham, loai_san_pham.id as ID_loai_san_pham, loai_san_pham.ten_loai_san_pham, san_pham.ngay_them, san_pham.ngay_cap_nhat FROM san_pham inner join san_pham_and_loai_san_pham on san_pham.ID = san_pham_and_loai_san_pham.ID_san_pham INNER JOIN loai_san_pham on loai_san_pham.id = san_pham_and_loai_san_pham.ID_loai_san_pham WHERE san_pham.ID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product.setID(rs.getInt("ID"));
                product.setTen_san_pham(rs.getString("ten_san_pham"));
                product.setGia_san_pham(rs.getDouble("gia_san_pham"));
                product.setMo_ta_san_pham(rs.getString("mo_ta_san_pham"));
                product.setMa_giam_gia(rs.getString("ma_giam_gia"));
                product.setSo_luong_san_pham(rs.getString("so_luong_san_pham"));
                product.setHinh_anh_san_pham(rs.getString("hinh_anh_san_pham"));
                product.setNgay_them(rs.getString("ngay_them"));
                product.setNgay_cap_nhat(rs.getString("ngay_cap_nhat"));
                product.setTen_loai_san_pham(rs.getString("ten_loai_san_pham"));
                product.setID_loai_san_pham(rs.getInt("ID_loai_san_pham"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }
}

