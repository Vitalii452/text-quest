function loadDialogue(dialogueOptionId) {
    fetch('getNextDialogue', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: 'dialogueOptionId=' + dialogueOptionId
    })
        .then(response => response.json())
        .then(dialogueData => {
            displayDialogue(dialogueData);
        })
        .catch(error => console.error('Error:', error));
}

function displayDialogue(dialogueData) {
    document.getElementById('dialogueText').textContent = dialogueData.text;
    const optionsContainer = document.getElementById('dialogueOptions');
    optionsContainer.innerHTML = '';

    dialogueData.dialogueOptions.forEach(option => {
        const button = document.createElement('button');
        button.textContent = option.text;
        button.onclick = option.nextDialogueId === 0
            ? () => loadLocation(option.id)
            : () => loadDialogue(option.id);

        optionsContainer.appendChild(button);
    });
}

function loadLocation(dialogueOptionId) {
    fetch('getNextLocation')
        .then(response => response.json())
        .then(locationData => {
            if (locationData.endOfGame) {
                window.location.href = 'endGame';
            } else {
                displayLocation(locationData);
                loadDialogue(dialogueOptionId);
            }
        })
        .catch(error => console.error('Error:', error));
}

function displayLocation(locationData) {
    document.getElementById('locationName').textContent = locationData.name;
    const descriptionContainer = document.getElementById('locationDescription');
    descriptionContainer.innerHTML = '';

    locationData.descriptionParagraph.forEach(paragraph => {
        const pElement = document.createElement('p');
        pElement.textContent = paragraph;
        descriptionContainer.appendChild(pElement);
    });
}
