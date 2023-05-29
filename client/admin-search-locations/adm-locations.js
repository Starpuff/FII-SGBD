const popup = document.getElementById('add-event-popup');
const addEventButton = document.querySelector('.add_event');
const closeButton = popup.querySelector('.close');
const addEventForm = document.getElementById('add-event-form');
const eventTypeInput = addEventForm.elements['event-visibility'];
const invitedPeopleContainer = document.getElementById('invited-people-container');
//import { locationData } from '../js/locations.js';

function openPopup() {
  popup.style.display = 'block';
}

function closePopup() {
  popup.style.display = 'none';
}

addEventButton.addEventListener('click', openPopup);
closeButton.addEventListener('click', closePopup);

eventTypeInput.addEventListener('change', function() {
  invitedPeopleContainer.style.display = (this.value === 'private') ? 'flex' : 'none';
});

addEventForm.addEventListener('submit', function(e) {
  e.preventDefault();

  const eventName = addEventForm.elements['event-name'].value;
  const eventLocation = addEventForm.elements['event-location'].value;
  const eventDate = addEventForm.elements['event-date'].value;
  // const parts = eventDate.split('-');
  // const transformedDate = `${parts[2]}-${parts[1]}-${parts[0]}`;
  const eventType = addEventForm.elements['event-visibility'].value;
  const invitedPeople = (eventType === 'private') ? addEventForm.elements['invited-people'].value : '';


  var formData = new FormData();
  formData.append('name', eventName);
  formData.append('date', eventDate);
  formData.append('type', eventType);
  formData.append('ticket_price', 0);
  formData.append('location_address', eventLocation);
  //formData.append('invitedPeople', invitedPeople);

  console.log(eventName);
  console.log(eventDate);
  console.log(eventType);
  console.log(eventLocation);

  fetch('http://localhost:5000/api/events', {
    method: 'POST',
    body: formData
  })
    .then(response => {
      if(response.status === 200) {
        alert('Event added successfully!');
        closePopup();
      }
      else {
        alert('Error adding event!');
      }
    })
    .catch(error => {
      alert('Error adding event!');
    });
    closePopup();
});

//add-locations-popup
const locationPopup = document.getElementById('add-location-popup');
const addLocationButton = document.querySelector('.add_location');
const closeLocationButton = locationPopup.querySelector('.close');
const addLocationForm = document.getElementById('add-location-form');
const descriptionContainer = document.getElementById('description-container');

function openLocationPopup() {
  locationPopup.style.display = 'block';    
}

function closeLocationPopup() {
  locationPopup.style.display = 'none';
}

addLocationButton.addEventListener('click', openLocationPopup);
closeLocationButton.addEventListener('click', closeLocationPopup);

addLocationForm.addEventListener('submit', function(e) {
  e.preventDefault();

  const locationName = addLocationForm.elements['location-name'].value;
  const locationAddress = addLocationForm.elements['location-address'].value;
  const locationCapacity = addLocationForm.elements['location-capacity'].value;
  const description = addLocationForm.elements['description'].value;

  console.log(locationName);
  console.log(locationAddress);
  console.log(locationCapacity);
  console.log(description);

  // const data = {
  //   name: locationName,
  //   address: locationAddress,
  //   capacity: locationCapacity,
  //   description: description
  // };
  var formData = new FormData();
  formData.append('name', locationName);
  formData.append('address', locationAddress);
  formData.append('capacity', locationCapacity);
  formData.append('description', description);

  fetch('http://localhost:5000/api/locations', {
    method: 'POST',
    body: formData
  })
    .then(response => {
      if(response.status === 200) {
        alert('Location added successfully!');
        closeLocationPopup();
        populateLocationGrid();
    }
    else {
        alert('An error occurred while adding the location');
    }})
    .catch(error => {
      console.error(error);
      alert('An error occurred while adding the location');
    });
    closeLocationPopup();
});


function populateLocationGrid() {
    const locationGrid = document.getElementById('location-grid');
    
    locationGrid.innerHTML = '';

    var formData = new FormData();
    formData.append('page', 1);
    formData.append('size', 1000);

    fetch('http://localhost:5000/api/locations/paginated', {
      method: 'POST',
    body: formData})
    .then(response => response.json())
    .then(data => {
      console.log(data);
      data.forEach(location => {
        const locationCard = document.createElement('div');
        locationCard.className = 'location';

        const locationName = document.createElement('h3');
        locationName.textContent = location.name;
        locationCard.appendChild(locationName);

        const locationAddress = document.createElement('p');
        locationAddress.textContent = `Location: ${location.address}`;
        locationCard.appendChild(locationAddress);

        const locationCapacity = document.createElement('p');
        locationCapacity.textContent = `Capacity: ${location.capacity}`;
        locationCard.appendChild(locationCapacity);

        const locationDescription = document.createElement('p');
        locationDescription.textContent = `Description: ${location.description}`;
        locationCard.appendChild(locationDescription);

        const deleteButton = document.createElement('div');
        deleteButton.className = 'delete-location-button'; 
        const delButton = createButton('delete-location','trash.png');
        deleteButton.appendChild(delButton);
        locationCard.appendChild(deleteButton);

        locationGrid.appendChild(locationCard);
    });
  });
}

function createButton(className, imageName) {
    const button = document.createElement('button');
    button.className = className;
  
    const img = document.createElement('img');
    img.setAttribute('src', `../images/${imageName}`);
  
    button.appendChild(img);
  
    // Add event listener for button click
    button.addEventListener('click', () => {
      // Toggle button color on click
      button.classList.toggle('button-clicked');
    });
  
    return button;
  }

populateLocationGrid();