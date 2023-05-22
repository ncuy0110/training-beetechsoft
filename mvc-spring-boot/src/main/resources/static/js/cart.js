$(document).ready(() => {
    $('.carousel-item:first').addClass('active');
});
const addToCartUnAuth = (id, quantity = 0) => {
    quantity = Number(quantity);
    const cartsCookie = $.cookie('carts');
    const carts = cartsCookie ? JSON.parse($.cookie('carts')) : {};
    if (quantity === 0) {
        quantity = Number($('#quantity').val());
    }
    if (id in carts) {
        carts[id] = carts[id] + quantity;
    } else {
        carts[id] = quantity;
    }
    $.cookie('carts', JSON.stringify(carts), {expires: 30, path: '/'});
    console.log(carts);
}

const addToCartAuth = (productId, quantity = 0) => {
    quantity = Number(quantity);
    if (quantity === 0) {
        quantity = Number($('#quantity').val());
    }
    // Get the access token from the cookie
    const accessToken = $.cookie('accessToken');

    const data = {productId, quantity};
    // Make the POST request with the access token in the headers
    $.ajax({
        url: '/api/v1/cart',
        type: 'POST',
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + accessToken);
        },
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (response) {
            alert('Add to cart successfully!');
        },
        error: function (error) {
            console.log(error);
            alert('Add failed!');
        }
    });
}