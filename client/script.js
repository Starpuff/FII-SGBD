document.getElementById("login-form").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent form submission
  
    // Check username and password (You may replace this with your own logic)
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if (username === "user" && password === "password") {
      // Redirect to search page
      window.location.href = "../search/search.html";
    } else {
      alert("Invalid username or password");
    }
  });
  