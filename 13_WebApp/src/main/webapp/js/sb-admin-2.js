(function($) {
  "use strict"; // Start of use strict

  // Toggle the side navigation
  $("#sidebarToggle, #sidebarToggleTop").on('click', function(e) {
    $("body").toggleClass("sidebar-toggled");
    $(".sidebar").toggleClass("toggled");
    if ($(".sidebar").hasClass("toggled")) {
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Close any open menu accordions when window is resized below 768px
  $(window).resize(function() {
    if ($(window).width() < 768) {
      $('.sidebar .collapse').collapse('hide');
    };
    
    // Toggle the side navigation when window is resized below 480px
    if ($(window).width() < 480 && !$(".sidebar").hasClass("toggled")) {
      $("body").addClass("sidebar-toggled");
      $(".sidebar").addClass("toggled");
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
  $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function(e) {
    if ($(window).width() > 768) {
      var e0 = e.originalEvent,
        delta = e0.wheelDelta || -e0.detail;
      this.scrollTop += (delta < 0 ? 1 : -1) * 30;
      e.preventDefault();
    }
  });

  // Scroll to top button appear
  $(document).on('scroll', function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('.scroll-to-top').fadeIn();
    } else {
      $('.scroll-to-top').fadeOut();
    }
  });

  // Smooth scrolling using jQuery easing
  $(document).on('click', 'a.scroll-to-top', function(e) {
    var $anchor = $(this);
    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top)
    }, 1000, 'easeInOutExpo');
    e.preventDefault();
  });
  
  //Evento del botón que me devuelve el listado de actores
  $("#btn-search-actors").click(function(){
		//alert("The button was clicked 1");
				
		$.ajax( {
			
			type: "GET",
			url: '/Grupo20/HelloServlet',
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlActorsList = '<ul>';
				$.each(data.actores, function(i,item){
					  htmlActorsList += '<li>' + item + '</li>';
				});
				htmlActorsList += '</ul>';
				$('#div-listado-actores').html("");
				$('#div-listado-actores').append(htmlActorsList);
			}
		} );
		
		
	});
	
	//Evento del botón que me devuelve el listado de películas de un determinado actor
	$("#btn-search-movies-by-actor").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/Grupo20/MoviesByActor?actor_name=' + $('#txt-actor').val(),
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlMovieList = '<ul>';
				$.each(data.peliculas, function(i,item){
					  htmlMovieList += '<li>' + item + '</li>';
				});
				htmlMovieList += '</ul>';
				$('#div-listado-actores').html("");
				$('#div-listado-actores').append(htmlMovieList);
			}
		} );
		
		
	});
	
	
	//Evento del botón que creara una nueva pelicula
	$("#btn-movie-insert").click(function(){
		
		//alert("Resultado: " + '/Grupo20/SaveMovieServlet?title=' + $('#txt-movie-title').val() + '&release_year=' + $('#txt-movie-release_year').val() + '&tagline=' + $('#txt-movie-tagline').val());
				
		$.ajax( {
			
			type: "GET",
			url: '/Grupo20/SaveMovieServlet?title=' + $('#txt-movie-title').val() + '&release_year=' + $('#txt-movie-release_year').val() + '&tagline=' + $('#txt-movie-tagline').val() ,
			success: function(data) {
			    alert("Resultado: " + data.resultado);
			}
		} );
		
		
	});

})(jQuery); // End of use strict
