<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create a New Set</title>
        <link rel="stylesheet" href="./assets/css/common.css">
        <link rel="stylesheet" href="./assets/css/create.css">
    </head>

    <body>
        <%@include file="./components/header.jsp" %>
        <form class="mt-5 set-creation" action="create-question-set" method="post">
            <div class="container">
                <div class="d-flex">
                    <h2 class="mb-4">Creating a new set</h2>
                    <button class="btn btn-primary ms-auto align-self-center" type="button">Create</button>
                </div>
                <div class="input-group input-group-lg display-6 mb-4">
                    <input name="questionSetName" type="text" class="form-control" placeholder="Enter the title for the set">
                </div>
                <div class="input-group mb-4">
                    <div class="description me-5">
                        <textarea class="form-control" rows="3" placeholder="Description (optional)"></textarea>
                    </div>
                    <div class="d-flex align-items-center ms-auto">
                        <label for="visibility" name="visibility" class="me-2 ms-auto">Visibility</label>
                        <select class="form-select" style="width: 140px" id="visibility">
                            <option value="public">Public</option>
                            <option value="private">Private</option>
                        </select>
                        <button class="btn btn-primary ms-2" data-bs-toggle="modal" data-bs-target="#importModal"
                                type="button" onclick="modalOpenClose()">Import</button>
                    </div>
                </div>
                <div id="term-set">
                    <!-- Terms will be added here by JavaScript -->
                </div>
                <div class="text-center mt-4">
                    <button type="button" class="btn btn-success" onclick="addTerm()">Add term</button>
                </div>

                <div class="mt-4">
                    <button type="submit" class="btn btn-primary">Create</button>
                </div>
            </div>
        </form>

        <div class="modal fade" id="importModal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2 class="modal-title text-center" id="exampleModalLabel">Import a set from an external site!</h2>
                        <button type="button" class="btn-close" onclick="modalOpenClose()" data-bs-dismiss="modal" aria-label="Close">X</button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="mb-3">
                                <label for="betweenTermAndDef" class="col-form-label">Between term and definition</label>
                                <input type="text" class="form-control" id="betweenTermAndDef" placeholder="Ex: ' -'"
                                       value=" - ">
                            </div>
                            <div class="mb-3">
                                <label for="betweenRows" class="col-form-label">Between rows</label>
                                <input class="form-control" id="betweenRows" placeholder="Ex: '\n\n'" value="\n\n"></input>
                            </div>
                            <div class="mb-3">
                                <label for="exportedSet" class="col-form-label">Exported set</label>
                                <textarea id="exportedSet" class="form-control"
                                          placeholder="Paste your exported set here..."></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" onclick="modalOpenClose()" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary btn-import ms-3">Import</button>
                    </div>

                </div>
            </div>
        </div>

        <script>
            let termNumber = 1;

            function addTerm(question, answer) {
                const termSetDiv = document.getElementById('term-set');
                question = question || '';
                answer = answer || '';
                const newTermHtml = `
                <div class="term rounded-2 bg-white mb-4 pb-1">
                    <div class="px-3 d-flex justify-content-between align-items-center">
                        <span>${termNumber}</span>
                        <button type="button" class="btn btn-sm btn-danger my-2" onclick="deleteTerm(this)">Delete</button>
                    </div>
                    <div class="input-group mb-4 px-4">
                        <textarea name="question" class="auto-resize-input form-control py-3 me-4" rows="1" placeholder="Enter Question..." oninput="resizeInput()"></textarea>
                        <textarea name="answer" class="auto-resize-input form-control py-3 ms-4" rows="1" placeholder="Enter Answer..." oninput="resizeInput()"></textarea>
                    </div>
                </div>
                `;
                termSetDiv.insertAdjacentHTML('beforeend', newTermHtml);
                const newTerm = termSetDiv.lastElementChild;
                newTerm.querySelector('textarea[name="question"]').value = question;
                newTerm.querySelector('textarea[name="answer"]').value = answer;
                termNumber++;
            }

            function deleteTerm(element) {
                element.closest('.term').remove();
                termNumber--;
                updateTermNumbers();
            }

            function updateTermNumbers() {
                const terms = document.querySelectorAll('.term');
                terms.forEach((term, index) => {
                    term.querySelector('span').innerText = (index + 1).toString();
                });
            }

            function importTerms() {
                let betweenTermAndDef = document.querySelector('#betweenTermAndDef').value;
                let betweenRows = document.querySelector('#betweenRows').value;
                const exportedSet = document.querySelector('#exportedSet').value;

                // Replace string representations of escape sequences with actual escape sequences
                betweenTermAndDef = betweenTermAndDef.replace(/\\n/g, '\n');
                betweenRows = betweenRows.replace(/\\n/g, '\n');

                // Split the set into lines
                const lines = exportedSet.split(betweenRows);
                console.log(lines);
                lines.forEach(line => {
                    if (line.trim() !== "") {
                        // Split each line into term and definition
                        const termAndDef = line.split(betweenTermAndDef);
                        if (termAndDef.length === 2) {
                            // Create and add term element
                            addTerm(termAndDef[0].trim(), termAndDef[1].trim());
                            console.log(termAndDef[0].trim(), termAndDef[1].trim());
                        }
                    }
                });
            }
            document.querySelector('#importModal .btn-import').addEventListener('click', importTerms);

            function resizeInput() {
                const inputs = document.querySelectorAll('.auto-resize-input');
                inputs.forEach(element => {
                    element.style.height = 'auto';
                    element.style.height = element.scrollHeight + 'px';
                });
            }

            // open modal
            function modalOpenClose() {
                document.querySelector('#importModal').classList.toggle("d-block");
            }
        </script>
    </body>

</html>
