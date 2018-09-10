$(function () {
    $dialog = $('.dialog');
    $closeButton = $('#close-button');
    $openButton = $('#open-button');
    $instructionsDialog = $('#instructions-dialog');
    $selectButton = $('#dialog-select-button');
    $formSelectbutton = $('#form-select-button');
    $page2SelectButton = $('#page2-select-button');

    $date = $('#date-number');

    $formListContainer = $('.form-list-container');

    $formSelectContainer = $('#form-selection');
    $formPage1 = $('#page-1');
    $formPage2 = $('#page-2');
    $formPage3 = $('#page-3');

    var selectedFormId = -1;
    var timer = 10000

    $openButton.click(function () {
        //TODO: load forms
        $dialog.fadeIn(200, function () {
            showInstructions();
        });
        setListClickListeners();
        $date.text(getDate());
    });

    function getDate() {
        var d = new Date();

        var month = d.getMonth() + 1;
        var day = d.getDate();

        return (month < 10 ? '0' : '') + month + '-' + (day < 10 ? '0' : '') + day + '-' + d.getFullYear();
    }

    $closeButton.click(function () {
        $dialog.fadeOut(150);
        dismissInstructions();
    });


    $instructionsDialog.click(function () {
        dismissInstructions();
    });

    $selectButton.click(function () {
        $formSelectContainer.fadeOut(200, function () {
            //TODO: Load form information
            $formPage1.fadeIn(200);
        });
    });

    $formSelectbutton.click(function () {
        $formPage1.fadeOut(200, function () {
            //TODO: Load form information
            $formPage2.fadeIn(200);
        });
    });

    $page2SelectButton.click(function () {
        $formPage2.fadeOut(200, function () {
            //TODO: Load form information
            $formPage3.fadeIn(200);
        });
    });

    function showInstructions() {
        $instructionsDialog.fadeIn(100);

        setTimeout(
            function () {
                dismissInstructions();
            }, timer);
    }

    function dismissInstructions() {
        $instructionsDialog.fadeOut(200);
    }

    function setListClickListeners() {
        $formListContainer.children('div').each(function () {
            $(this).click(function () {
                $(this).addClass('active');
                $(this).removeClass('form-list-hover');

                if (selectedFormId == -1) {
                    $selectButton.fadeIn(200);
                } else {
                    $('#' + selectedFormId).removeClass('active');
                    $('#' + selectedFormId).addClass('form-list-hover');
                }

                selectedFormId = $(this).attr('id');
            });
        });
    }


});
