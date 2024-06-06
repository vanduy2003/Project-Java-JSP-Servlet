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
            <form action="LoginServlet" method="post">
                <h2>Login</h2>
                <div class="inputbox">
                    <ion-icon name="mail-outline"></ion-icon>
                    <input type="email" name="email" required>
                    <label>Email</label>
                </div>
                <div class="inputbox">
                    <ion-icon name="lock-closed-outline"></ion-icon>
                    <input type="password" name="password" required>
                    <label>Password</label>
                </div>
                <div class="forget justify-content-between">
                    <label><input type="checkbox">Remember Me</label>
                    <label><a href="#">Forget Password?</a></label>
                </div>
                <button type="submit">Log in</button>
                <div class="register">
                    <p>Don't have an account? <a href="#">Register</a></p>
                </div>
            </form>
        </div>
    </div>
</section>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>
