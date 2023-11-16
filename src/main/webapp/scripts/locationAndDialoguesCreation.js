let paragraphCount = 1;
let dialogueCount = 0;

function addParagraph() {
    paragraphCount++;
    const paragraphsDiv = document.getElementById("paragraphs");
    const newParagraph = `<label for="paragraph${paragraphCount}">Параграф ${paragraphCount}:</label>
                          <input type="text" id="paragraph${paragraphCount}" name="paragraphs">`;
    paragraphsDiv.insertAdjacentHTML('beforeend', newParagraph);
}

function addDialogue() {
    dialogueCount++;
    const dialoguesDiv = document.getElementById("dialogues");
    const newDialogue = `<div id="dialogue${dialogueCount}">
                             <h3>Диалог ${dialogueCount}:</h3>
                             <label for="dialogueText${dialogueCount}">Текст диалога:</label>
                             <input type="text" id="dialogueText${dialogueCount}" name="dialogueTexts">
                             <div id="dialogueOptions${dialogueCount}">
                             </div>
                             <button type="button" onclick="addDialogueOption(${dialogueCount})">Добавить опцию диалога</button>
                         </div>`;
    dialoguesDiv.insertAdjacentHTML('beforeend', newDialogue);
}

function addDialogueOption(dialogueId) {
    const dialogueOptionsDiv = document.getElementById(`dialogueOptions${dialogueId}`);
    const newOption = `<div>
                           <label for="optionText${dialogueId}">Текст опции:</label>
                           <input type="text" id="optionText${dialogueId}" name="optionTexts${dialogueId}">
                           <label for="nextDialogueId${dialogueId}">Номер следующего диалога:</label>
                           <input type="number" id="nextDialogueId${dialogueId}" name="nextDialogueIds${dialogueId}">
                           <label for="score${dialogueId}">Очки:</label>
                           <input type="number" id="score${dialogueId}" name="scores${dialogueId}">
                       </div>`;
    dialogueOptionsDiv.insertAdjacentHTML('beforeend', newOption);
}

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("createQuestForm").addEventListener("submit", function(event) {
        event.preventDefault();

        const questData = {
            locationName: document.getElementById("locationName").value,
            descriptionParagraphs: Array.from(document.getElementsByName("paragraphs")).map(input => input.value),
            dialogues: collectDialogsData()
        };

        fetch('createLocationAndDialogues', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(questData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.redirect) {
                    window.location.href = data.redirect;
                }
            })
            .catch(error => console.error('Error:', error));
    });
});

function collectDialogsData() {
    const dialogues = [];
    for (let i = 1; i <= dialogueCount; i++) {
        const dialogue = {
            text: document.getElementById(`dialogueText${i}`).value,
            dialogueOptions: collectDialogueOptions(i)
        };
        dialogues.push(dialogue);
    }
    return dialogues;
}

function collectDialogueOptions(dialogueId) {
    const dialogueOptions = [];
    const optionTexts = document.getElementsByName(`optionTexts${dialogueId}`);
    const nextDialogueIds = document.getElementsByName(`nextDialogueIds${dialogueId}`);
    const scores = document.getElementsByName(`scores${dialogueId}`);

    for (let i = 0; i < optionTexts.length; i++) {
        const option = {
            text: optionTexts[i].value,
            nextDialogueId: parseInt(nextDialogueIds[i].value),
            score: parseInt(scores[i].value)
        };
        dialogueOptions.push(option);
    }
    return dialogueOptions;
}
