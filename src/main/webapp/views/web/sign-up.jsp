<%--
  Created by IntelliJ IDEA.
  User: duy95
  Date: 29/05/2024
  Time: 11:13 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: duy95
  Date: 29/05/2024
  Time: 11:13 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="css/login.css">
    <title>Login</title>
</head>
<body>
<section>
    <div class="form-box">
        <div class="form-value">
            <form action="">
                <h2>Login</h2>
                <div class="inputbox">
                    <ion-icon name="mail-outline"></ion-icon>
                    <input type="email" required>
                    <label for="">Email</label>
                </div>
                <div class="inputbox">
                    <ion-icon name="lock-closed-outline"></ion-icon>
                    <input type="password" required>
                    <label for="">Password</label>
                </div>
                <div class="forget justify-content-between">
                    <label for=""><input type="checkbox">Remember Me</label>
                    <label for=""><a href="#">Forget Password?</a></label>
                </div>
                <button>Log in</button>
                <div class="register">
                    <p>Don't have a account <a href="#">Register</a></p>
                </div>
            </form>
        </div>
    </div>
</section>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>

