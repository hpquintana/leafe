<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=.5, maximum-scale=.5, user-scalable=no">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300,700|Roboto+Slab:100" rel="stylesheet">
    <link rel="stylesheet" href="css/dashboard.css">
    <link rel="stylesheet" href="css/m.dashboard.css">
    <link rel="stylesheet" href="css/animation.css">
    <link rel="stylesheet" href="css/dialog.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="js/dialog.js"></script>
</head>

<body style="overflow: hidden;">
    <div class="toolbar raised bottom-border flex-container">
        <div class="toolbar-square-button right-border button-hover gray-background">
            <img src="img/menu_icon.png" />
        </div>
        <div class="toolbar-search-container right-border gray-background">

        </div>
        <div class="toolbar-logo-container right-border gray-background">

        </div>
        <div class="toolbar-square-button right-border button-hover gray-background">
            <img src="img/notifications_icon.png" />
        </div>
        <div class="toolbar-square-button right-border button-hover gray-background" id="open-button">
            <img src="img/new_form_icon.png" />
        </div>
        <div class="toolbar-square-button button-hover gray-background">
            <img src="img/sign_out_icon.png" />
        </div>
    </div>

    <div class="full-screen-container gray-background flex-container centered-flex">
        <div class="primary-action-container green-background flex-container right-grid-margin">
            <div class="primary-action-icon-container text-align-center">
                <img class='primary-action-icon' src="img/open_jobs_icon.png" />
            </div>
            <div class='primary-action-info-container'>
                <p class="primary-action-title white-text">0</p>
                <p class="primary-action-subtitle white-text">open jobs</p>
                <p class="primary-action-text white-text">Lorem Ipsum dolor sit amet, vix no aliqi</p>
            </div>
        </div>
        <div class="primary-action-container blue-background flex-container left-grid-margin right-grid-margin">
            <div class="primary-action-icon-container text-align-center">
                <img class='primary-action-icon' src="img/open_jobs_icon.png" />
            </div>
            <div class='primary-action-info-container'>
                <p class="primary-action-title white-text">4,200</p>
                <p class="primary-action-subtitle white-text">forms processed</p>
                <p class="primary-action-text white-text">Lorem Ipsum dolor sit amet, vix no aliqi</p>
            </div>
        </div>
        <div class="primary-action-container yellow-background flex-container left-grid-margin right-grid-margin">
            <div class="primary-action-icon-container text-align-center">
                <img class='primary-action-icon' src="img/open_jobs_icon.png" />
            </div>
            <div class='primary-action-info-container'>
                <p class="primary-action-title white-text">$123K</p>
                <p class="primary-action-subtitle white-text">income</p>
                <p class="primary-action-text white-text">Lorem Ipsum dolor sit amet, vix no aliqi</p>
            </div>
        </div>
        <div class="primary-action-container red-background flex-container left-grid-margin ">
            <div class="primary-action-icon-container text-align-center">
                <img class='primary-action-icon' src="img/open_jobs_icon.png" />
            </div>
            <div class='primary-action-info-container'>
                <p class="primary-action-title white-text">27</p>
                <p class="primary-action-subtitle white-text">notifications</p>
                <p class="primary-action-text white-text">Lorem Ipsum dolor sit amet, vix no aliqi</p>
            </div>
        </div>
    </div>

    <div class="dialog">
        <div class="dialog-container relative-container text-align-center">
            <img id="close-button" class="close-button" src="img/close_button.png" />
            <img id="spinner" class="spinner" src="img/loading.gif" />
            <div class="meta-data-container">
                <p class="invoice-number">0 <span id="date-number"></span></p>

            </div>
            <div class="dialog-information" id="form-selection">
                <img class="dialog-icon" src="img/forms_icon.png" />
                <h2 class="dialog-title">YOUR FORMS</h2>
                <div class="form-list-container">
                    <div class="form-list-element form-list-hover" id="0">
                        <p>Invoice Form</p>
                    </div>
                    <div class="form-list-element form-list-hover" id="1">
                        <p>New Employee Form</p>
                    </div>
                    <div class="form-list-element form-list-hover" id="2">
                        <p>New Order Form</p>
                    </div>
                    <div class="form-list-element form-list-hover" id="3">
                        <p>Commercial Contract</p>
                    </div>
                </div>
                <div class="dialog-subcontainer">
                    <p style="text-align: right;">New Form</p>
                </div>
                <div class="dialog-button" id="dialog-select-button">
                    <p class="dialog-button-text">SELECT</p>
                </div>
                <div class="dialog-instructions" id="instructions-dialog">
                    <p>Select a form to get started, or create a new form</p>
                </div>
            </div>
            <div class="dialog-information" id="page-1">
                <h2 class="dialog-title large-title">CUSTOMER DETAILS</h2>
                <div class="flex-container customer-details-container">
                    <div class="details-container">
                        <h2>Customer Information</h2>
                        <p>First Name</p>
                        <input type="text" id="fname" name="name">

                        <p>Last Name</p>
                        <input type="text" id="fname" name="name">

                        <p>Phone Number</p>
                        <input type="text" id="fname" name="name">

                        <p>Email</p>
                        <input type="text" id="fname" name="name">
                    </div>
                    <div class="details-container middle-border">
                        <h2>Origin Address</h2>
                        <p>Address</p>
                        <input type="text" id="fname" name="name">

                        <p>City</p>
                        <input type="text" id="fname" name="name">

                        <p>State</p>
                        <input type="text" id="fname" name="name">

                        <p>Zip Code</p>
                        <input type="text" id="fname" name="name">
                    </div>
                    <div class="details-container">
                        <h2>Destination Address</h2>
                        <p>Address</p>
                        <input type="text" id="fname" name="name">

                        <p>City</p>
                        <input type="text" id="fname" name="name">

                        <p>State</p>
                        <input type="text" id="fname" name="name">

                        <p>Zip Code</p>
                        <input type="text" id="fname" name="name">
                    </div>
                </div>
                <div class="dialog-button" id="form-select-button">
                    <p class="dialog-button-text">CONTINUE</p>
                </div>
            </div>
            <div class="dialog-information" id="page-2">
                <h2 class="dialog-title large-title">ORDER DETAILS</h2>
                <div class="flex-container customer-details-container">
                    <div class="details-container">
                        <h2>Move Information</h2>
                        <p>Date of move</p>
                        <input type="date" id="date" name="date">

                        <p>Type of proposal provided</p>
                        <input type="checkbox" id="c1" name="cc" />
                        <label class="checkbox-label" for="c1">Binding</label>
                        <input type="checkbox" id="c1" name="cc" />
                        <label class="checkbox-label" for="c1">Not-to-exceed</label>

                        <p>Hour minimum</p>
                        <input type="checkbox" id="c1" name="cc" />
                        <label class="checkbox-label" for="c1">2 hour min.</label>
                        <input type="checkbox" id="c1" name="cc" />
                        <label class="checkbox-label" for="c1">4 hour min.</label>

                        <p>Trip Charge</p>
                        <input type="text" id="tripCharge" name="tripCharge">
                    </div>
                    <div class="details-container middle-border">
                        <h2>Moving Services</h2>
                        <p>Trucks</p>
                        <input type="text" id="fname" name="name">

                        <p>Movers</p>
                        <input type="text" id="fname" name="name">
                    </div>
                    <div class="details-container">
                        <h2>Additional Products</h2>
                        <label class="checkbox-label" for="c1">Book boxes</label>
                        <input type="checkbox" id="c1" name="cc" />
                        <label class="checkbox-label" for="c1">Medium boxes</label>
                        <input type="checkbox" id="c1" name="cc" />
                        <br/>

                        <label class="checkbox-label" for="c1">Large boxes</label>
                        <input type="checkbox" id="c1" name="cc" />
                        <label class="checkbox-label" for="c1">XL boxes</label>
                        <input type="checkbox" id="c1" name="cc" />
                        <br/>

                        <label class="checkbox-label" for="c1">Dish barrels</label>
                        <input type="checkbox" id="c1" name="cc" />
                        <label class="checkbox-label" for="c1">Warddrobes</label>
                        <input type="checkbox" id="c1" name="cc" />
                        <br/>

                        <label class="checkbox-label" for="c1">Paper pads</label>
                        <input type="checkbox" id="c1" name="cc" />
                        <label class="checkbox-label" for="c1">Shrink wrap</label>
                        <input type="checkbox" id="c1" name="cc" />
                        <br/>

                        <label class="checkbox-label" for="c1">Tape</label>
                        <input type="checkbox" id="c1" name="cc" />
                        <label class="checkbox-label" for="c1">Newsprint</label>
                        <input type="checkbox" id="c1" name="cc" />
                        <br/>

                        <label class="checkbox-label" for="c1">Picture Boxes</label>
                        <input type="checkbox" id="c1" name="cc" />
                    </div>
                </div>
                <div class="dialog-button" id="page2-select-button">
                    <p class="dialog-button-text">CONTINUE</p>
                </div>
            </div>
            <div class="dialog-information" id="page-3">
                <h2 class="dialog-title large-title">Assign Order</h2>
                <div class="flex-container assign-order-container">

                </div>
                <div class="dialog-button" id="form-select-button">
                    <p class="dialog-button-text">CONTINUE</p>
                </div>
            </div>
        </div>

    </div>
</body>

</html>
