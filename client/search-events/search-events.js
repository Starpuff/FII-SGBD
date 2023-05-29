const popup = document.getElementById('add-event-popup');
const addEventButton = document.querySelector('.add_event');
const closeButton = popup.querySelector('.close');
const addEventForm = document.getElementById('add-event-form');
const eventTypeInput = addEventForm.elements['event-visibility'];
console.log(eventTypeInput);
const invitedPeopleContainer = document.getElementById('invited-people-container');
import { eventData } from '../js/events.js';

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
        populateEventGrid();
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

    const eventDate = document.createElement('p');
    eventDate.textContent = `Date: ${event.date}`;
    eventElement.appendChild(eventDate);

    const eventLocation = document.createElement('p');
    eventLocation.textContent = `Address: ${event.location_name}`;
    eventElement.appendChild(eventLocation);

    const eventVisibility = document.createElement('p');
    eventVisibility.textContent = `Visibility: ${event.type}`;
    eventElement.appendChild(eventVisibility);

    if (event.visibility === 'private') {
      const privateButtons = document.createElement('div');
      privateButtons.className = 'private-event-buttons';

      const attendButton = createButton('attend-event', 'tick.png');
      const notAttendingButton = createButton('not-attending-event', 'cross.png');

      privateButtons.appendChild(attendButton);
      privateButtons.appendChild(notAttendingButton);

      eventElement.appendChild(privateButtons);
    } else if (event.visibility === 'public') {
      const publicButton = document.createElement('div');
      publicButton.className = 'public-event-button';

      const attendButton = createButton('attend-event', 'tick.png');

      publicButton.appendChild(attendButton);
      eventElement.appendChild(publicButton);
    }

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
