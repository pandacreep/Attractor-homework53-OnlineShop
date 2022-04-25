<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Adding new Producer</h1>
    <form action="/addProducer" method="post">
        <fieldset>
            <div class="form-element">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" required autofocus>
            </div>
            <br>
            <div class="form-element">
                <label for="country">Country</label>
                <input type="text" name="country" id="country" required>
            </div>
            <br>
            <div class="form-element">
                <button class="action-button" type="submit">Add</button>
            </div>
        </fieldset>
    </form>
</@base.layout>