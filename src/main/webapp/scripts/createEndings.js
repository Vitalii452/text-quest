function addEnding() {
    var endingsContainer = document.getElementById('endingsContainer');
    var newEndingHtml = `
        <div class="ending">
            <input type="number" name="minScore" placeholder="Min Score">
            <input type="number" name="maxScore" placeholder="Max Score">
            <textarea name="text" placeholder="Ending Text"></textarea>
        </div>
    `;
    endingsContainer.insertAdjacentHTML('beforeend', newEndingHtml);
}

function submitEndings() {
    var endings = [];
    document.querySelectorAll('.ending').forEach(function(endingDiv) {
        var ending = {
            minScore: endingDiv.querySelector('[name="minScore"]').value,
            maxScore: endingDiv.querySelector('[name="maxScore"]').value,
            text: endingDiv.querySelector('[name="text"]').value
        };
        endings.push(ending);
    });

    fetch('/addEndings', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({endings: endings})
    })
        .then(response => response.json())
        .then(data => {
            if (data.redirect) {
                window.location.href = data.redirect;
            }
        })
        .catch(error => console.error('Error:', error));
}
