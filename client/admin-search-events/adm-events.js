const popup = document.getElementById('add-event-popup');
const addEventButton = document.querySelector('.add_event');
const closeButton = popup.querySelector('.close');
const addEventForm = document.getElementById('add-event-form');
const eventTypeInput = addEventForm.elements['event-visibility'];
console.log(eventTypeInput);
const invitedPeopleContainer = document.getElementById('invited-people-container');
//import { eventData } from '../js/events.js';

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
  const eventType = addEventForm.elements['event-type'].value;
  const invitedPeople = (eventType === 'private') ? addEventForm.elements['invited-people'].value : '';

  alert('Event added successfully!');

  closePopup();
});



// Function to populate the event grid
function populateEventGrid() {
  const eventGrid = document.getElementById('event-grid');

  // Clear existing content in the event grid
  eventGrid.innerHTML = '';

  var formData = new FormData();
  formData.append('page',1);
  formData.append('size',1000);

  fetch('http://localhost:5000/api/events/paginated', {
    method: 'POST',
    body: formData})
  .then(response => response.json())
  .then(data => {
    console.log(data);
    data.forEach(event => {

    const eventElement = document.createElement('div');
    eventElement.className = 'event';

    const eventName = document.createElement('h3');
    eventName.textContent = event.name;
    eventElement.appendChild(eventName);

    const eventLocation = document.createElement('p');
    eventLocation.textContent = `Location: ${event.location}`;
    eventElement.appendChild(eventLocation);

    const eventDescription = document.createElement('p');
    eventDescription.textContent = `Description: ${event.description}`;
    eventElement.appendChild(eventDescription);

    const deleteButton = document.createElement('div');
    deleteButton.className = 'delete-event-button';
    const delButton = createButton('delete-event', 'trash.png');
    deleteButton.appendChild(delButton);
    eventElement.appendChild(deleteButton);

    eventGrid.appendChild(eventElement);
  });
  });
}

// Helper function to create a button element
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

// Call the function to populate the event grid
populateEventGrid();


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

  alert('Location added successfully!');

  closeLocationPopup();
});
