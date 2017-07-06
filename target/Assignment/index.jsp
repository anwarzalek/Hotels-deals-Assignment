<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <script>
        jQuery(document).ready(function ($) {
                $("form").submit(function () {
                    $(this).find(":input").filter(function () {
                        return !this.value;
                    }).attr("disabled", "disabled");
                    return true;
                });
                $("form").find(":input").prop("disabled", false);
            }
        )
    </script>
</head>
<body>
<form action="HotelsForm" method="get" style="padding: 30px;">
    Destination city: <input type="text" name="destinationName" />
    <br/>
    Min trip start date: <input type="date" name="minTripStartDate"/>
    <br/>
    Max trip start date: <input type="date" name="maxTripStartDate"/>
    <br/>
    Length of stay: <input type="number" name="lengthOfStay"/>
    <br/>
    <div data-role="rangeslider">
        <label>Min/Max star rating :</label>
        <input type="range" name="minStarRating" id="minStarRating" value="0" min="0" max="3" step="0.5">
        <input type="range" name="maxStarRating" id="maxStarRating" value="3" min="0" max="3" step="0.5">
    </div>
    <br/>
    Min total rate: <input type="number" name="minTotalRate"/>
    <br/>
    Max total rate: <input type="number" name="maxTotalRate"/>
    <br/>
    <div data-role="rangeslider">
        <label>Min/Max guest rating :</label>
        <input type="range" name="minGuestRating" id="minGuestRating" value="0" min="0" max="5" step="0.5">
        <input type="range" name="maxGuestRating" id="maxGuestRating" value="5" min="0" max="5" step="0.5">
    </div>
    <br/>
    <input type="submit" value="Search"/>
</form>
</body>
</html>