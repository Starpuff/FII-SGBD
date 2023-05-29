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

  // Iterate over the event data and create event elements
  eventData.forEach(event => {
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
