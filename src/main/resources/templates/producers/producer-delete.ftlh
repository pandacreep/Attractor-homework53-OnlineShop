<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Deleting producer</h1>
    <form action="/deleteProducer" method="post">
        <fieldset>
            <h2>Are you sure, you want to delete producer?</h2>
            <div class="form-element">
                <input type="hidden" name="producerId" value="${producer.id}">
            </div>
            <p>Name: <i>${producer.name}</i></p>
            <p>Country: <i>${producer.country}</i></p>
            <#if producerUsedInItems>
                <p>Actually this product is used in Items. Please delete all corresponding items first</p>
            <#else>
                <div class="form-element">
                    <button class="action-button" type="submit">Delete</button>
                </div>
            </#if>
        </fieldset>
    </form>
    <#if producerUsedInItems>
        <p><a href="/producers">return back</a></p>
    <#else>
        <p>Please confirm! or <a href="/producers">return back</a></p>
    </#if>
</@base.layout>