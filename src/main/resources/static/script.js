function selectMessage(e) {
    console.log("got to select message");
    var userInput = document.getElementById('result').value;

    fetch('http://localHost:8080/messages', {
        method: 'PUT',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            message: e.getMessages(),
            status: PROCESSED,
            wasProcessed: true,
            publishDate: null,
        })
    }).then((res) => res.json())
        .then((data) => alert('Your message id is ' + data))
        .catch((err) => console.log(err))

    //how do i get the selected message here?
    put(`http://localHost:8080/messages/${message.id}`, message)
        .then(value => {
            if (value.success) {
                deleteObject('tray', tray);
                sendBacktoList();
                // showSuccessEdit();
                // setTimeout(sendBacktoList, 3000);
            } else {
                console.log('failed to update tray');
            }
        });
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
                            <input type="checkbox" onclick="selectMessage(this)"> Message : ${msg.message}</input>
                        </li>
                      </ul>
                    </div>`;
            });
            document.getElementById('result').innerHTML = result;
            console.log(result);

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



