function pageLoad() {
    addSkills();
    addAbilities();
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

function addAbilities(){
  $.ajax({
      url:'/ability/list',
      type:'GET',
      success: abilityList => {
          let abilityHTML = `<div class = container>`


          +`</div>`;

          for(let ability of abilityList)

              $('#abilityList').html(abilityHTML);
      }
  })
}

function addSkills() {
    let x = 1;
    let sm = "sm";
    let ab = "ab";
    let ar = "ar";
    let mm = "mm";

    $.ajax({
        url: '/skills/list',
        type: 'GET',
        success: skillsList => {
            let skillHTML = `<div class="container">`
                + `<div class="row mb-2">`
                + `<div class="col-2 bg-light font-weight-bold">Skill</div>`
                + `<div class="col-2 bg-light font-weight-bold">Key Ability</div>`
                + `<div class="col-2 bg-light font-weight-bold">Skill Modifier</div>`
                + `<div class="col-2 bg-light font-weight-bold">Ability Modifier</div>`
                + `<div class="col-2 bg-light font-weight-bold">Ability Ranks</div>`
                + `<div class="col-2 bg-light font-weight-bold">Misc Modifier</div>`
                + `</div>`;

            for (let skill of skillsList)

                skillHTML += `<div class="row col-form-label row-striped">`
                + `<div class="col-2">${skill.skillName}</div>`
                + `<div class="col-2">${skill.keyAbility}</div>`
                + `<div id=`+sm.concat(x.toString())+`class="col-2"><input type="text"></div>`
                + `<div id=`+ab.concat(x.toString())+`class="col-2"><input type="text"></div>`
                + `<div id=`+ar.concat(x.toString())+`class="col-2"><input type="number" min="0" max="100"></div>`
                + `<div id=`+mm.concat(x.toString())+`class="col-2"><input type="text"></div>`
                + `</div>`;
                //x++;
            skillHTML += `</div>`;

            $('#skillsList').html(skillHTML);

        }
    });
}

