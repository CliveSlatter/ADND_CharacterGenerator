function pageLoad() {
    deleteRow();
}

function GenerateAbilityScores() {
    console.log("Button pressed");
    let total = 0;
    let low = 20;
    for (let a = 0; a < 6; a++) {
        let rolls = [];

        for (let b = 0; b < 4; b++) {
            rolls[b] = Math.floor((Math.random() * 6) + 0.5);
            console.log(rolls[b]);
            if (rolls[b] < low) low = rolls[b];
            total += rolls[b];
        }
        total -= low;
        switch (a) {
            case 0:
                document.getElementById("str_as").value = total.toString();
                break;
            case 1:
                document.getElementById("dex_as").value = total.toString();
                break;
            case 2:
                document.getElementById("con_as").value = total.toString();
                break;
            case 3:
                document.getElementById("int_as").value = total.toString();
                break;
            case 4:
                document.getElementById("wis_as").value = total.toString();
                break;
            case 5:
                document.getElementById("cha_as").value = total.toString();
        }
        total = 0;
        low = 20;
    }
}

