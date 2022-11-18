call mvn verify -D cucumber.filter.tags="@smoke" -D browser=chromeHeadless
call mvn verify -D cucumber.filter.tags="@smoke" -D browser=firefox
call mvn verify -D cucumber.filter.tags="@smoke" -D browser=edge