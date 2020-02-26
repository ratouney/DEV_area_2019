$( function() {
    $( "#draggable" ).draggable({ axis: "y" });
    $( "#draggable2" ).draggable({ axis: "x" });

    $( "#draggable3" ).draggable({ handle: "p"}, {containment: "#containment-wrapper", scroll: true });
    $( "#draggable4" ).draggable({ handle: "p"}, {containment: "#containment-wrapper", scroll: false });
} );
