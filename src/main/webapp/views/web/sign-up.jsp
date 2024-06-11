<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="css/login.css">
    <title>Register</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap');
        *{
            margin: 0;
            padding: 0;
            font-family: 'poppins',sans-serif;
        }
        section{
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            width: 100%;
            background: url('https://cdn.pixabay.com/photo/2021/06/29/17/47/cherries-6374733_1280.png') no-repeat;
            background-position: center;
            background-size: cover;
        }
        .form-box-1{
            position: relative;
            width: 460px;
            height: 620px;
            background: transparent;
            border: 2px solid rgba(255,255,255,0.5);
            border-radius: 20px;
            backdrop-filter: blur(15px);
            display: flex;
            justify-content: center;
            align-items: center;
        }
        h2{
            font-size: 2em;
            color: #fff;
            text-align: center;
        }
        .inputbox{
            position: relative;
            margin: 30px 0;
            width: 390px;
            border-bottom: 2px solid #fff;
        }
        .inputbox label{
            position: absolute;
            top: 50%;
            left: 5px;
            transform: translateY(-50%);
            color: #fff;
            font-size: 1em;
            pointer-events: none;
            transition: .5s;
        }
        input:focus ~ label,
        input:valid ~ label{
            top: -5px;
        }
        .inputbox input {
            width: 100%;
            height: 50px;
            background: transparent;
            border: none;
            outline: none;
            font-size: 1em;
            padding: 0 35px 0 5px;
            color: #fff;
        }
        .inputbox ion-icon{
            position: absolute;
            right: 8px;
            color: #fff;
            font-size: 1.2em;
            top: 20px;
        }
        .forget{
            margin: -15px 0 15px;
            font-size: .9em;
            color: #fff;
            display: flex;
            justify-content: space-between;
        }
        .forget label input{
            margin-right: 3px;
        }
        .forget label a{
            color: #fff;
            text-decoration: none;
        }
        .forget label a:hover{
            text-decoration: underline;
        }
        button{
            width: 100%;
            height: 40px;
            border-radius: 40px;
            background: #fff;
            border: none;
            outline: none;
            cursor: pointer;
            font-size: 1em;
            font-weight: 600;
        }
        .register{
            font-size: .9em;
            color: #fff;
            text-align: center;
            margin: 25px 0 10px;
        }
        .register p a{
            text-decoration: none;
            color: #fff;
            font-weight: 600;
        }
        .register p a:hover{
            text-decoration: underline;
        }
        /* Toast styles */
        #toast {
            visibility: hidden;
            max-width: 350px;
            background-color: #fff;
            color: #333;
            text-align: left;
            border-radius: 5px;
            position: fixed;
            z-index: 1;
            bottom: 30px;
            right: 30px;
            font-size: 16px;
            white-space: nowrap;
            padding: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: flex;
            align-items: center;
        }
        #toast.show {
            visibility: visible;
            -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
            animation: fadein 0.5s, fadeout 0.5s 2.5s;
        }
        #toast ion-icon {
            margin-right: 10px;
            font-size: 24px;
        }
        #toast.success {
            border-left: 4px solid green;
        }
        #toast.success ion-icon {
            color: green;
        }
        #toast.success .toast-title {
            color: green;
        }
        #toast.error {
            border-left: 4px solid red;
        }
        #toast.error ion-icon {
            color: red;
        }
        #toast.error .toast-title {
            color: red;
        }
        #toast .toast-title {
            font-weight: bold;
            margin-right: 10px;
        }
        @-webkit-keyframes fadein {
            from {bottom: 0; opacity: 0;}
            to {bottom: 30px; opacity: 1;}
        }
        @keyframes fadein {
            from {bottom: 0; opacity: 0;}
            to {bottom: 30px; opacity: 1;}
        }
        @-webkit-keyframes fadeout {
            from {bottom: 30px; opacity: 1;}
            to {bottom: 0; opacity: 0;}
        }
        @keyframes fadeout {
            from {bottom: 30px; opacity: 1;}
            to {bottom: 0; opacity: 0;}
        }

    </style>
</head>
<body>
<section>
    <div class="form-box-1">
        <div class="form-value">
            <form action="dang-ky" method="post">
                <h2>Register</h2>
                <div class="inputbox">
                    <ion-icon name="person-outline"></ion-icon>
                    <input type="text" name="username" required>
                    <label>Username</label>
                </div>
                <div class="inputbox">
                    <ion-icon name="mail-outline"></ion-icon>
                    <input type="email" name="email" required>
                    <label>Email</label>
                </div>
                <div class="inputbox">
                    <ion-icon name="phone-portrait-outline"></ion-icon>
                    <input type="text" name="sdt" required>
                    <label>Phone</label>
                </div>
                <div class="inputbox">
                    <ion-icon name="lock-closed-outline"></ion-icon>
                    <input type="password" name="password" required>
                    <label>Password</label>
                </div>
                <div class="inputbox">
                    <ion-icon name="lock-closed-outline"></ion-icon>
                    <input type="password" name="confirm_password" required>
                    <label>Confirm Password</label>
                </div>
                <button type="submit">Register</button>
                <div class="register">
                    <p>You already have an account? <a href="dang-nhap">Login</a></p>
                </div>
            </form>
        </div>
    </div>
</section>

<!-- Toast container -->
<div id="toast">
    <ion-icon name=""></ion-icon>
    <div>
        <div class="toast-title"></div>
        <div class="toast-message"></div>
    </div>
</div>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', (event) => {
        const toast = document.getElementById("toast");
        const icon = toast.querySelector("ion-icon");
        const title = toast.querySelector(".toast-title");
        const message = toast.querySelector(".toast-message");

        const toastMessage = '<%= request.getAttribute("message") %>';
        const toastTitle = '<%= request.getAttribute("title") %>';
        const toastIcon = '<%= request.getAttribute("icon") %>';
        const messageType = '<%= request.getAttribute("messageType") %>';
        const redirect = '<%= request.getAttribute("redirect") %>';

        if (toastMessage && toastTitle && toastIcon) {
            icon.setAttribute("name", toastIcon);
            title.textContent = toastTitle;
            message.textContent = toastMessage;

            // Remove all previous messageType classes
            toast.classList.remove("success", "error");

            // Add the current messageType class
            if (messageType) {
                toast.classList.add(messageType);
            }

            toast.classList.add("show");

            // Remove the show class after 3 seconds
            setTimeout(function() {
                toast.classList.remove("show");
                if (redirect === "true") {
                    window.location.href = "/Project_JSP_Servlet_war_exploded/";
                }
            }, 3000);
        }
    });

</script>
</body>
</html>
