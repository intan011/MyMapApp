<?php
    include("db.php");
    $conn;
    mysqli_select_db($conn, "perlishospital");

    // Retrieve staff names for the sidebar
    $staffNames = array();
    $staffQuery = "SELECT staff_name FROM staff";
    $staffResult = mysqli_query($conn, $staffQuery);
    while ($row = mysqli_fetch_assoc($staffResult)) {
        $staffNames[] = $row['staff_name'];
    }

    // Retrieve data from the gpslocation table
    $gpsData = array();
    $gpsQuery = "SELECT id, user_name, timestamp, user_agent, lat, lng FROM gpslocation";
    $gpsResult = mysqli_query($conn, $gpsQuery);
    while ($row = mysqli_fetch_assoc($gpsResult)) {
        $gpsData[] = $row;
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
        }

        #sidebar {
            width: 250px;
            height: 100%;
            background-color: #2c3e50;
            color: #ecf0f1;
            position: fixed;
            overflow: hidden;
            transition: width 0.5s;
        }

        #sidebar h3 {
            padding: 20px 10px;
            margin-bottom: 20px;
            text-align: center;
            color: #3498db;
        }

        #sidebar ul {
            list-style-type: none;
            padding: 0;
        }

        #sidebar li {
            padding: 15px;
            border-bottom: 1px solid #34495e;
            transition: background-color 0.3s;
        }

        #sidebar li:hover {
            background-color: #34495e;
        }

        #main-content {
            flex: 1;
            padding: 20px;
            transition: margin-left 0.5s;
            margin-left: 250px; /* Adjusted margin to the right */
        }

        h2 {
            padding-left: 70px; /* Left padding for the title */
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            padding-left: 70px; /* Left padding for the table */
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #2c3e50;
            color: #ecf0f1;
        }

        #menu-icon {
            font-size: 30px;
            cursor: pointer;
            position: fixed;
            padding: 15px 20px;
            color: #3498db;
            z-index: 1;
        }

        #logout-btn {
            cursor: pointer;
            padding: 15px;
            border-top: 1px solid #34495e;
            transition: background-color 0.3s;
            display: block;
            width: 100%;
            box-sizing: border-box;
            text-align: center;
            line-height: 1.5;
        }

        #logout-btn:hover {
            background-color: #34495e;
        }
    </style>
</head>
<body>
    <div id="sidebar">
        <h3>Hi!</h3>
        <ul>
            <?php foreach ($staffNames as $staffName): ?>
                <li><?php echo $staffName; ?></li>
            <?php endforeach; ?>
        </ul>
        <li id="logout-btn" onclick="location.href='stafflogout.php'">Logout</li> <!-- Logout button with link to login.php -->
    </div>

    <div id="main-content">
        <span id="menu-icon">&#9776;</span>
        <h2>Main Page</h2>
        <table>
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Time</th>
                    <th>User Agent</th>
                    <th>GPS Coordinates</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach ($gpsData as $index => $data): ?>
                    <tr>
                        <td><?php echo $index + 1; ?></td>
                        <td><?php echo $data['user_name']; ?></td>
                        <td><?php echo $data['timestamp']; ?></td>
                        <td><?php echo $data['user_agent']; ?></td>
                        <td><?php echo $data['lat'] . ', ' . $data['lng']; ?></td>
                    </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    </div>

    <script>
        document.getElementById('menu-icon').addEventListener('click', function () {
            toggleSidebar();
        });

        function toggleSidebar() {
            var sidebar = document.getElementById('sidebar');
            var mainContent = document.getElementById('main-content');
            
            if (sidebar.style.width === '250px') {
                sidebar.style.width = '0';
                mainContent.style.marginLeft = '0';
            } else {
                sidebar.style.width = '250px';
                mainContent.style.marginLeft = '250px';
            }
        }
    </script>
</body>
</html>
