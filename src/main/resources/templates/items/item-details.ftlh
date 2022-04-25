<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Information about item</h1>
    <div class="row">
        <div class="col-left">
            <form class="width100" action="/detailsItem" method="post">
                <h3 class="form_header">Detailed information</h3>
                <fieldset>
                    <p>Id: <i>${item.id}</i></p>
                    <p>Name: <i>${item.name}</i></p>
                    <p>Price: <i>${item.price}</i></p>
                    <p>Description: <i>${item.description}</i></p>
                    <p>Producer: <i>${item.getProducer().name}</i></p>
                    <p>Category: <i>${item.getCategory().name}</i></p>
                    <#if image??>
                        <img alt="item picture" src="${'data:image/png;base64,'+image}" />
                    </#if>
                </fieldset>
            </form>
        </div>
        <div class="col-right">
            <form class="width100" action="/addReview" method="post">
                <h3 class="form_header">Your review</h3>
                <fieldset>
                    <div class="form-element">
                        <input type="hidden" name="itemId" value="${item.id}">
                    </div>
                    <div class="form-element">
                        <label for="reviewerName">Full name</label>
                        <input type="text" name="reviewerName" id="reviewerName" required autofocus>
                    </div>
                    <br>
                    <div class="form-element">
                        <label for="phone">Phone</label>
                        <input type="number" name="phone" id="phone" required>
                    </div>
                    <br>
                    <div class="form-element">
                        <label for="email">E-mail</label>
                        <input type="text" name="email" id="email" required>
                    </div>
                    <br>
                    <div class="form-element">
                        <label for="stars">Raiting (1-5)</label>
                        <input type="text" name="stars" id="stars" pattern="[1-5]{1}" required>
                    </div>
                    <br>
                    <div>
                        <label for="review">Review</label><br>
                        <textarea rows="3" cols="25" name="review"></textarea>
                    </div>
                    <div class="form-element">
                        <button class="action-button" type="submit">Add</button>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
    <div class="row">
        <h3>Reviews list</h3>
        <#list reviews as review>
            <hr>
            Reviewer: ${review.reviewerName}; phone: ${review.phone}; e-mail: ${review.email}:<br>
            Rating: ${review.stars}; comment: ${review.review}
        </#list>
        <br>
        <br>
    </div>
</@base.layout>