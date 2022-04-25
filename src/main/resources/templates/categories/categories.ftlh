<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Categories list for our items</h1>
    <p>You can add new category here <a class="as-button" href="/categories/add"> add </a></p>
    <p>Note: You cannot delete category if there is an item with this category</p>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        <#list categories as key, value>
            <tr>
                <td>${key.id}</td>
                <td>${key.name}</td>
                <td>${key.description}</td>
                <td>
                    <a class="as-button" href="/categories/${key.id}/details">Details</a>
                    <a class="as-button" href="/categories/${key.id}/edit">Edit</a>
                    <#if !value>
                        <a class="as-button" href="/categories/${key.id}/delete">Delete</a>
                    </#if>
                </td>
            </tr>
        </#list>
    </table>
</@base.layout>