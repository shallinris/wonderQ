//send a put request to the back end with the status field set to PROCESSED
function selectMessage() {
    console.log("got to select message");
    var input = document.getElementById('result').valueOf();

    fetch('http://localHost:8080/messages/new', {
        method: 'PUT',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            message: input,
            status: "PROCESSED",
            wasProcessed: true,
            publishDate: null,
        })
    }).then((res) => res.json())
        .then((data) => alert('Your message id is ' + data))
        .catch((err) => console.log(err))
}

function getMessages() {
    let result;
    fetch('http://localHost:8080/messages/new')
        .then((resp) => resp.json())
        .then(data => {
            data.forEach(msg => {
                result +=
                    `<div>
                      <ul>
                        <li>
                            <input type="checkbox" onclick="selectMessage()"> Message : ${msg.message}</input>
                        </li>
                      </ul>
                    </div>`;
            });
            document.getElementById('result').innerHTML = result;

        })
        .catch(error => {
            console.log(error);
        });
}

function postMessage(event) {
    event.preventDefault();
    console.log("got to post message");
    var userInput = document.getElementById('message').value;

    userInput === "" ?
        alert('Please enter a message') :
        fetch('http://localHost:8080/messages', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                message: userInput,
                status: null,
                wasProcessed: false,
                publishDate: new Date(),
            })
        }).then((res) => res.json())
            .then((data) => alert('Your message id is ' + data))
            .catch((err) => console.log(err))
}
