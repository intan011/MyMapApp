<?php
session_start();

// Check if the user is already logged out
if (empty($_SESSION['staff_username'])) {
    echo "<script>alert('You have been logged out.');</script>";
} else {
    // Destroy the session
    session_destroy();

    // Echo a logout message using JavaScript
    echo "<script>alert('You are already logged out.');</script>";
}

// Redirect to the login page after a short delay
echo "<script>setTimeout(function() { window.location='index.php'; }, 2000);</script>";
?>
