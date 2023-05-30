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

const deleteEventButton = document.querySelector('.delete-event-button');
deleteEventButton.addEventListener('click', deleteEvent);

function deleteEvent() {
    console.log('buttonPressed');
    const id = document.getElementById('event-name').value;
    console.log(id);
    
    var url = 'http://localhost:5000/api/locations/id/' + id;
    fetch(url, {
      method: 'DELETE',
    })
      .then(response => {console.log(response); return response.json()})
      .then(data => {
        console.log(data);
        alert('Event deleted successfully!');
        populateEventGrid();
      }
      )
      .catch(error => {
        alert('Error deleting event!');
      }
      );
}

//search by event id

const searchEventById = document.querySelector('.event-id-button');

searchEventById.addEventListener('click', getById);

function getById() {
  console.log('buttonPressed');
  
  const id = document.getElementById('event-name').value;
  console.log(id);
  const eventGrid = document.getElementById('event-grid');
  eventGrid.innerHTML = '';

  var url = 'http://localhost:5000/api/events/id/' + id;
  fetch(url, {
    method: 'GET',
  })
    .then(response => {console.log(response); return response.json()})
    .then(data => {
      console.log(data);
      
      data.forEach(event => {
        const eventElement = document.createElement('div');
        eventElement.className = 'event';

        const eventName = document.createElement('h3');
        eventName.textContent = event.name;
        eventElement.appendChild(eventName);

        const eventDate = document.createElement('p');
        eventElement.appendChild(eventDate);

        const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
        const dateParts = new Date(event.date).toLocaleDateString('en-US', options).split('/');
        const formattedDate = `${dateParts[2]}-${dateParts[0].padStart(2, '0')}-${dateParts[1].padStart(2, '0')}`;

        eventDate.textContent = `Date: ${formattedDate}`;

        eventLocation.textContent = `Address: ${event.location_name}`;
        const eventLocation = document.createElement('p');
        
        eventElement.appendChild(eventLocation);
        eventVisibility.textContent = `Visibility: ${event.type}`;
        const eventVisibility = document.createElement('p');
        
        eventElement.appendChild(eventVisibility);
    // const eventDescription = document.createElement('p');
    // eventDescription.textContent = `Description: ${event.description}`;
    // eventElement.appendChild(eventDescription);

        const deleteButton = document.createElement('div');
        deleteButton.className = 'delete-event-button';
        const delButton = createButton('delete-event', 'trash.png');
        deleteButton.appendChild(delButton);
        eventElement.appendChild(deleteButton);

        eventGrid.appendChild(eventElement);
      });
    })
    .catch(error => {
      alert('Error getting locations!');
    });
}

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

    // const eventDescription = document.createElement('p');
    // eventDescription.textContent = `Description: ${event.description}`;
    // eventElement.appendChild(eventDescription);

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
