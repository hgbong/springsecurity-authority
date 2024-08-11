function changeUserRole() {
    const userId = document.getElementById('userSelect').value;
    const roleName = document.getElementById('roleSelect').value;

    if (!userId || !roleName) {
        alert('Please select both user and roleName.');
        return;
    }

    const data = {
        userId: userId,
        roleNames: [roleName]
    };

    const url = `/admin/users/${userId}/roles`
    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            if (response.status == 403) {
                alert('not enough authority. (403)');
                return;
            }
            
            return response.text().then(errMsg => {
                alert('Role change failed. reason:\r\n' + errMsg);
            })
        }
        return response.json();
    })
    .then(data => {
        if (!data) {
            return;
        }
        alert('Role changed successfully. data: ' + data);
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to change role. reason:\r\n ' + error);
    });
}
