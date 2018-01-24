Feature: Test

  Background: Open https://www.wiley.com/en-ru
    When user is on the page "Homepage | Wiley"
    * user $close the undetected country dialog

  Scenario: 1. Check the following links are displayed in the top menu
    * element "Resources" is displayed
    * element "Subjects" is displayed
    * element "About" is displayed

  Scenario: 2. Check items under Resources for sub-header
    * element "Resources" has 10 sub-items
    * element "Resources" contains "Students, Instructors, Researchers, Professionals, Librarians" sub-items
    * element "Resources" contains "Institutions, Authors, Resellers, Corporations, Societies" sub-items

  Scenario: 3. Check “Students” page
    * click on the element by the items chain: Resources >>> Students
    * user is on the page "Students | Wiley"
    * current URL is "https://www.wiley.com/en-ru/students"
    * element "Header" is displayed
    * current page has the link: "http://wileyplus.wiley.com/" "Learn more."

  Scenario: 4. Check “Education” page
    * click on the element by the items chain: Subjects >>> E-L >>> Education
    * user is on the page "Education | Subjects | Wiley"
    * side panel "Side Panel" has 13 items
    * side panel "Side Panel" contains "Information & Library Science" item
    * side panel "Side Panel" contains "Education & Public Policy" item
    * side panel "Side Panel" contains "K-12 General" item
    * side panel "Side Panel" contains "Higher Education General" item
    * side panel "Side Panel" contains "Vocational Technology" item
    * side panel "Side Panel" contains "Conflict Resolution & Mediation (School settings)" item
    * side panel "Side Panel" contains "Curriculum Tools- General" item
    * side panel "Side Panel" contains "Special Educational Needs" item
    * side panel "Side Panel" contains "Theory of Education" item
    * side panel "Side Panel" contains "Education Special Topics" item
    * side panel "Side Panel" contains "Educational Research & Statistics" item
    * side panel "Side Panel" contains "Literacy & Reading" item
    * side panel "Side Panel" contains "Classroom Management" item

  Scenario: 5. Click on the Wiley logo at the top menu
    * user $click on the element by title "Logo"
    * user is on the page "Homepage | Wiley"
    * current URL is "https://www.wiley.com/en-ru"

  Scenario: 6. Do not enter anything in the search input and press search button
    * user $search ""
    * user is on the page "Homepage | Wiley"
    * current URL is "https://www.wiley.com/en-ru"

  Scenario: 7. Enter “Math” and do not press search button
    * user $fill the input field "Search Input" "Math"
    * result preview "Result Preview" has 4 list items which starting with "Math"
    * result preview "Result Preview" has 4 product items which contains "Math" in titles
    * element "Search Input" is above an another element "Result Preview"

  Scenario: 8. Click “SEARCH” button
    * user $search "Math"
    * user is on the page "Results | Wiley"
    * products list "Products List" has 10 products
    * products list "Products List" has at least one “Add to Cart” button for each product.

  Scenario: 9. Enter “Math” in the search input at the top and press “SEARCH” button
    * user $search "Math"
    * user is on the page "Results | Wiley"
    * products list "Products List" has 10 products
    * element "Products List" is displayed