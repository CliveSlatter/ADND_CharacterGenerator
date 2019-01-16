function pageLoad() {
    GenerateAbilityScores();
    deleteRow();
}

function GenerateAbilityScores() {
    const genAbi = $('#abilities');
    genAbi.submit(event => {
        event.preventDefault();
        $.ajax({
            url: '/abilities/scores',
            type: 'POST',
            data: genAbi.serialize(),
            success: response => {
                if (response.startsWith('Error:')) {
                    alert(response);
                } else {
                    let total = 0;
                    let low = 20;
                    for (let a = 0; a < 6; a++) {
                        let rolls = [];

                        for (let b = 0; b < 4; b++) {
                            rolls[b] = (Math.random() * 6) + 0.5;
                            console.log(rolls[b]);
                            if (rolls[b] < low) low = rolls[b];
                            total += rolls[b];
                        }
                        total -= low;
                        switch (a) {
                            case 0:
                                document.getElementById("str_as").innerText = total.toString();
                                break;
                            case 1:
                                document.getElementById("dex_as").innerText = total.toString();
                                break;
                            case 2:
                                document.getElementById("con_as").innerText = total.toString();
                                break;
                            case 3:
                                document.getElementById("int_as").innerText = total.toString();
                                break;
                            case 4:
                                document.getElementById("wis_as").innerText = total.toString();
                                break;
                            case 5:
                                document.getElementById("cha_as").innerText = total.toString();
                        }
                        total = 0;
                        low = 20;
                    }
                }
            }
        });
    });

}

