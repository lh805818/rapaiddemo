jQuery.extend({
handleError: function( s, xhr, status, e ) {
// If a local callback was specified, fire it
if ( s.error )
s.error( xhr, status, e );
// If we have some XML response text (e.g. from an AJAX call) then log it in the console
else if(xhr.responseText)
console.log(xhr.responseText);
}
});