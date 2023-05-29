const popup = document.getElementById('add-event-popup');
const addEventButton = document.querySelector('.add_event');
const closeButton = popup.querySelector('.close');
const addEventForm = document.getElementById('add-event-form');
const eventTypeInput = addEventForm.elements['event-visibility'];
console.log(eventTypeInput);
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
  const eventType = addEventForm.elements['event-type'].value;
  const invitedPeople = (eventType === 'private') ? addEventForm.elements['invited-people'].value : '';

  alert('Event added successfully!');

  closePopup();
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

      locationGrid.appendChild(locationCard);
  });
  });
}

populateLocationGrid();
