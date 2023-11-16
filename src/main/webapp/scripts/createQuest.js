document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('submitQuest').addEventListener('click', function() {
        const questName = document.getElementById('questName').value;

        fetch('addQuest', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name: questName })
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Something went wrong');
                }
            })
            .then(data => {
                console.log(data);
            })
            .catch(error => console.error('Error:', error));
    });
});
