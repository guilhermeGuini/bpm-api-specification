(function(global) {

  var uiTemplate = {

    divWorkflow : (name, sequence) => {
      let workflowDiv = document.createElement('div');
      workflowDiv.id = 'workflow-' + sequence;
      workflowDiv.classList = 'workflow';

      let title = document.createElement('h3');
      title.textContent = name;

      let workflowMainDiv = document.createElement('div');
      workflowMainDiv.id = 'workflow-main-' + sequence;

      let workflowUnsatisfiedArrow = document.createElement('div');
      workflowUnsatisfiedArrow.id = 'workflow-unsatisfied-arrow-' + sequence;

      let workflowUnsatisfiedContent = document.createElement('div');
      workflowUnsatisfiedContent.id = 'workflow-unsatisfied-content-' + sequence;

      workflowDiv.append(title);
      workflowDiv.append(workflowMainDiv);
      workflowDiv.append(workflowUnsatisfiedArrow);
      workflowDiv.append(workflowUnsatisfiedContent);

      return workflowDiv;
    }

  }

  var view = {

    startSequence : 0,
    endSequence : 0,
    arrowSequence : 0,
    operationSequence : 0,
    conditionSequence : 0,

    createCircle : (fill, sequence) => {
      let canvas = document.createElement('canvas');
      canvas.id = 'start-' + sequence;
      canvas.width = 50;
      canvas.height = 100;

      var ctx = canvas.getContext('2d');
      var circle = new Path2D();
      circle.arc(canvas.width / 2, canvas.height / 2, 25, 0, 2 * Math.PI);

      ctx.closePath();

      if (fill) {
        ctx.fill(circle);
      } else {
        ctx.stroke(circle);
      }

      return canvas;
    },

    createStart : () => {
      return view.createCircle(false, view.startSequence++);
    },

    createEnd : () => {
      return view.createCircle(true, view.endSequence++);
    },

    createVerticalArrow : (color) => {

      let canvas = document.createElement('canvas');
      canvas.id = 'canvas-arrow-' + view.arrowSequence++;
      canvas.width = 100;
      canvas.height = 100;
  
      let context = canvas.getContext("2d");

      context.beginPath();
      context.moveTo(50, 0);
      context.lineTo(50, 90);
      context.lineTo(40, 90);
      context.lineTo(50, 100);
      context.lineTo(60, 90);
      context.lineTo(50, 90);

      context.closePath();

      context.stroke();

      if (color) {
        context.fillStyle = color;
      }

      context.fill();

      return canvas;
    },

    createArrow : (color) => {
      let canvas = document.createElement('canvas');
      canvas.id = 'canvas-arrow-' + view.arrowSequence++;
      canvas.width = 100;
      canvas.height = 100;
      
      let context = canvas.getContext("2d");

      context.beginPath();
      context.moveTo(0, 50);
      context.lineTo(90, 50);
      context.lineTo(90, 40);
      context.lineTo(100, 50);
      context.lineTo(90, 60);
      context.lineTo(90, 50);

      context.closePath();

      context.stroke();

      if (color) {
        context.fillStyle = color;
      }

      context.fill();

      return canvas;
    },

    createProcessingOperation : (operation) => {
      let canvas = document.createElement('canvas');
      canvas.id = 'canvas-operation-' + view.operationSequence++;
      canvas.width = 200;
      canvas.height = 100;
      canvas.style = 'border:1px solid #000000;';
      
      let context = canvas.getContext("2d");
      context.font = "12px Arial";
      context.textAlign = "center";
      context.fillText(operation.name, canvas.width/2, canvas.height/2);

      return canvas;
    },

    createConditionOperation : (operation) => {
      let canvas = document.createElement('canvas');
      canvas.id = 'canvas-condition-' + view.conditionSequence++;
      canvas.width = 100;
      canvas.height = 100;
      
      let context = canvas.getContext("2d");

      context.beginPath();
      context.moveTo(0, 50);
      context.lineTo(50, 0);
      context.lineTo(100, 50);
      context.lineTo(50, 100);
      context.lineTo(0, 50);
      context.closePath();
      context.stroke();

      context.font = "12px Arial";
      context.textAlign = "center";
      context.fillText(operation.name, canvas.width/2, canvas.height/2);

      return canvas;
    },

    createOperation : (operation) => {
      
      if (operation.type === 'PROCESSING') {
        return view.createProcessingOperation(operation);
      }

      return view.createConditionOperation(operation);
    },

    createWorkflow : (workflow, sequence) => {
      let contentDiv = document.getElementById('content');
      let divWorkflow = uiTemplate.divWorkflow(workflow.name, sequence);
      contentDiv.append(divWorkflow);

      let mainDiv = document.getElementById('workflow-main-' + sequence);
      let unsatisfiedArrowDiv = document.getElementById('workflow-unsatisfied-arrow-' + sequence);
      let unsatisfiedDiv = document.getElementById('workflow-unsatisfied-content-' + sequence);

      mainDiv.append(view.createStart());
      mainDiv.append(view.createArrow());

      let operation = workflow.operation;
      let conditionOperation;
      let maringArrow = 50;

      let currentOperation = operation;
      while (currentOperation) {
        mainDiv.append(view.createOperation(currentOperation));
        mainDiv.append(view.createArrow(currentOperation.type === 'PROCESSING' ? '' : 'green'));
        
        if (!conditionOperation) {
          if (currentOperation.type === 'CONDITION') {
            conditionOperation = currentOperation;    
          }      

          maringArrow += 200;
        } 

        currentOperation = currentOperation.next;
      }

      mainDiv.append(view.createEnd());

      currentOperation = conditionOperation ? conditionOperation.nextUnsatisfiedCondition : null;

      if (currentOperation) {
        unsatisfiedArrowDiv.append(view.createVerticalArrow('red'));
        unsatisfiedArrowDiv.style = 'margin-left: ' + maringArrow + 'px';

        unsatisfiedDiv.style = 'margin-left: ' + (maringArrow + 25) + 'px';

        while (currentOperation) {
          unsatisfiedDiv.append(view.createOperation(currentOperation));
          unsatisfiedDiv.append(view.createArrow(currentOperation.type === 'PROCESSING' ? '' : 'green'));
          currentOperation = currentOperation.next;
        }
  
        unsatisfiedDiv.append(view.createEnd());
      }
    }
  }

  var service = {

    url : 'http://localhost:8080/api/bpm/workflow',
    // url : 'http://localhost:8000/test',

    getWorkflows : () => {
      var headers = new Headers();
      headers.append('content-type', 'application/json');
      headers.append('Access-Control-Allow-Origin', '*');
    
      const options = {
          method: 'GET',
          headers: headers,
          mode: 'no-cors'
        };

      fetch(service.url, options).then(response => {
        console.log(response);
          if (response.ok) {
              return response.json();
          } else{
              console.error('Error to search workflow');
          }
        }).then(json => { 
          json.forEach((workflow, index) => {
            view.createWorkflow(workflow, index);
          })
        }).catch(() => {
          console.log("Booo");
        });
    }
  }

  var app = {

    init: () => {
      service.getWorkflows();
    }

  }

app.init();

})(this);