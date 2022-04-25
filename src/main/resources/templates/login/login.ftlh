<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Authorization</h1>
    <p>To continue buying you need to login</p>
    <p>Please select one of the account (email) below to login</p>
    <form action="/login" method="post">
        <fieldset>
            <div class="form-element">
                <label for="userId">User</label>
                <select name="userId" id="userId" autofocus>
                    <#list logins as login>
                        <option value="${login.email}">${login.fullName} (${login.email})</option>
                    </#list>
                </select>
            </div>
            <br>
            <div class="form-element">
                <button class="action-button" type="submit">Login</button>
            </div>
        </fieldset>
    </form>
</@base.layout>