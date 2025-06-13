<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Space Exploration: Journey Beyond the Stars</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to bottom, #000428, #004e92);
            color: #ffffff;
        }
        header {
            text-align: center;
            padding: 50px 20px;
            background: rgba(0, 0, 0, 0.7);
        }
        header h1 {
            font-size: 3em;
            margin-bottom: 10px;
        }
        header p {
            font-size: 1.2em;
            color: #ccc;
        }
        section {
            padding: 40px 60px;
        }
        .card {
            background: rgba(255, 255, 255, 0.1);
            border-radius: 10px;
            padding: 20px;
            margin: 20px 0;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
            transition: transform 0.3s;
        }
        .card:hover {
            transform: translateY(-10px);
        }
        .card h2 {
            color: #00c6ff;
        }
        .footer {
            text-align: center;
            padding: 20px;
            background: rgba(0, 0, 0, 0.7);
            font-size: 0.9em;
            color: #aaa;
        }
        a.button {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 10px;
            color: #fff;
            background: #00c6ff;
            border-radius: 5px;
            text-decoration: none;
            transition: background 0.3s;
        }
        a.button:hover {
            background: #0072ff;
        }
    </style>
</head>
<body>

<header>
    <h1>🚀 Space Exploration</h1>
    <p>Journey Beyond the Stars — Discover the wonders of our universe</p>
    <a href="#learn-more" class="button">Learn More</a>
</header>

<section>
    <div class="card">
        <h2>The Solar System</h2>
        <p>Our solar system is a fascinating place filled with planets, moons, asteroids, and comets. From the scorching surface of Mercury to the icy reaches of Neptune, every celestial body tells a unique story about the formation of our cosmic neighborhood.</p>
    </div>

    <div class="card">
        <h2>Deep Space Missions</h2>
        <p>Humanity has sent probes and rovers beyond the confines of our home planet to explore the depths of space. Missions like Voyager, New Horizons, and the Mars rovers have revolutionized our understanding of the cosmos.</p>
    </div>

    <div class="card">
        <h2>Future Colonization</h2>
        <p>As technology advances, the dream of establishing human colonies on Mars, the Moon, or even beyond becomes more achievable. Visionary projects by organizations like NASA and SpaceX aim to push the boundaries of human presence in the universe.</p>
    </div>
</section>

<div class="footer">
    &copy; 2025 Space Exploration Society. All rights reserved.
</div>

</body>
</html>
