function resetLoginForm() {
    const loginForm = $('#loginForm');
    loginForm.submit(event => {
        event.preventDefault();

        $.ajax({
            url: '/user/login',
            type: 'POST',
            data: loginForm.serialize(),
            success: response => {
                if (response.startsWith('Error:')) {
                    alert(response);
                } else {
                    Cookies.set("sessionToken", response);
                    window.location.href = "../client/index.html";
                }
            }
        });
    });
}

function resetNewUserForm() {
    const newUserForm = $('#newUserForm');
    newUserForm.submit(event => {
        event.preventDefault();
        $.ajax({
            url: '/user/new',
            type: 'POST',
            data: newUserForm.serialize(),
            success: response => {
                if (response.startsWith('Error:')) {
                    alert(response);
                } else {
                    Cookies.set("sessionToken", response);
                    window.location.href = "../index.html";
                }
            }
        });
    });
}

function socialMediaLogin() {
    const socialMediaForm = $('#socialMedia');

    socialMediaForm.submit(event => {
        console.log("Media login button clicked");

    });
}

function pageLoad() {
    resetLoginForm();
    resetNewUserForm();
    socialMediaLogin();
}
