document.querySelector("DOMContentLoaded", function() {
    function confirmDelete(event) {
        // Hiển thị hộp thoại xác nhận
        if (confirm("Bạn có chắc chắn muốn xóa người dùng này không?")) {
            // Nếu người dùng chọn "OK", biểu mẫu sẽ được gửi
            return true;
        } else {
            // Nếu người dùng chọn "Cancel", biểu mẫu sẽ không được gửi
            event.preventDefault();
            return false;
        }
    }
})