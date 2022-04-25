<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Editing producer</h1>
    <form action="/editProducer" method="post">
        <fieldset>
            <div class="form-element">
                <input type="hidden" name="producerId" value="${producer.id}">
            </div>
            <div class="form-element">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" value="${producer.name}" required autofocus>
            </div>
            <br>
            <div class="form-element">
                <label for="country">Country</label>
                <input type="text" name="country" id="country" value="${producer.country}" required>
            </div>
            <br>
            <div class="form-element">
                <button class="action-button" type="submit">Edit</button>
            </div>
        </fieldset>
    </form>
</@base.layout>
