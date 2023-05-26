document.getElementById("login-form").addEventListener("submit", function(event) {
  event.preventDefault(); // Prevent form submission

  var formData = new FormData();
  formData.append("username", document.getElementById("username").value);
  formData.append("password", document.getElementById("password").value);

  fetch('http://localhost:5000/api/login', {
    method: 'POST',
    body: formData
  })
  .then(function(response) {
    return response.json();
  })
  .then(function(data) {
    // Process the response data
    if (data === -1) {
      alert("Invalid username or password");
    } else {
      // Redirect to search page or perform any other action based on the user id
      window.location.href = "../search/search.html?id=" + data;
    }
  })
  .catch(function(error) {
    console.error('Error:', error);
  });
});
