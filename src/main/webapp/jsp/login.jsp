<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página Inicial - MindFlow</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Marcellus&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="login.css">
</head>
<body>
    <div class="container">
        <!-- Sidebar -->
        <aside class="sidebar">
            <div class="logo">
                <h1>MindFlow</h1>
            </div>
            <nav class="nav">
                <ul>
                    <li><a href="#" class="active"><i class="icon-home"></i> Home</a></li>
                    <li><a href="#"><i class="icon-profile"></i> Profile</a></li>
                    <li><a href="#"><i class="icon-heart"></i> Favourites <span class="badge">15</span></a></li>
                    <li><a href="#"><i class="icon-chat"></i> Chats</a></li>
                    <li><a href="#"><i class="icon-friends"></i> Friends</a></li>
                    <li><a href="#"><i class="icon-wallet"></i> Wallet</a></li>
                </ul>
            </nav>
        </aside>

        <!-- Main Content -->
        <main class="main-content">
            <header class="header">
                <input type="search" placeholder="Search...">
                <div class="user-options">
                    <span>ENGLISH</span>
                    <div class="notifications">🔔</div>
                    <div class="profile">HI, JACK</div>
                </div>
            </header>

            <section class="recommended-events">
                <h2>Recommended Events</h2>
                <div class="event">
                    <img src="https://via.placeholder.com/300x150" alt="Event Image">
                    <div class="event-info">
                        <h3>GLADIATOR VS KNIGHTS</h3>
                        <p>Group Stage</p>
                    </div>
                </div>
            </section>

            <section class="news-archive">
                <h2>News Archive</h2>
                <div class="news-item">
                    <img src="https://via.placeholder.com/150x200" alt="News Image">
                    <div class="news-info">
                        <h3>Historical Games</h3>
                        <p>Warring factions have brought the Origin System to the brink of destruction.</p>
                        <a href="#">View More</a>
                    </div>
                </div>
            </section>
        </main>
    </div>
</body>
</html>
