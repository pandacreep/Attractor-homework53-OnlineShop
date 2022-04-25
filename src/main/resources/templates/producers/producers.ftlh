<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Producers list for our items</h1>
    <p>You can add new producer here <a class="as-button" href="/producers/add"> add </a></p>
    <p>Note: You cannot delete producer if there is an item with this producer</p>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Country</th>
            <th>Actions</th>
        </tr>
        <#list producers as key, value>
            <tr>
                <td>${key.id}</td>
                <td>${key.name}</td>
                <td>${key.country}</td>
                <td>
                    <a class="as-button" href="/producers/${key.id}/details">Details</a>
                    <a class="as-button" href="/producers/${key.id}/edit">Edit</a>
                    <#if !value>
                        <a class="as-button" href="/producers/${key.id}/delete">Delete</a>
                    </#if>
                </td>
            </tr>
        </#list>
    </table>
</@base.layout>