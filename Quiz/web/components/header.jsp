
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>


    .container {
        width: 100%;
        margin: 0 auto;
    }

    header {
        padding: 1.5rem 0;
        background-color: #FFFFFF;
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 30px;

    }

    header .logo img {
        width: 120px;
        height: auto;
    }

    header .nav {
        list-style: none;
        display: flex;
        margin: 0;
        padding: 0;
    }

    header .nav li  {
        padding: 0 30px;
    }

    header .nav li a {
        font-weight: 600;
        font-size:x-large;
        text-decoration: none;
        color: #000;
        cursor: pointer;
        transition:  color 0.3s ;
    }

    header .nav li a:hover {

        color: #9B68FF;
    }



</style>




<main>
    <div class="container">
        <header>
            <div class="logo">
                <a href="#" class="text-dark text-decoration-none">
                    <img src="../assets/img/quiz-maker.svg" alt="Quizme Logo">
                </a>
            </div>




            <div>
                <ul class="nav">
                    <li><a href="#" class="link-secondary">Profile</a></li>
                    <li><a href="#" class="link-dark">Create</a></li>
                    <li><a href="#" class="link-dark">Log Out</a></li>
                </ul>
            </div>


        </header>
    </div>
</main>


