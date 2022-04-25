<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Details information for producer</h1>
    <form action="/detailsProducer" method="post">
        <fieldset>
            <p>Id: <i>${producer.id}</i></p>
            <p>Name: <i>${producer.name}</i></p>
            <p>Price: <i>${producer.country}</i></p>
        </fieldset>
    </form>
    <p>return back to the list of <a href="/producers">producers</a></p>
</@base.layout>