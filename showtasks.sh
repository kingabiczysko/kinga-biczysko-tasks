#!/usr/bin/env bash

open_Safari() {
  if  open -a Safari; then
     echo "Successfully open Safari"
     end
  else
     echo "Cannot open Safari"
     fail
  fi
}

open_Tasks(){
if open http://localhost:8080/crud/v1/task/getTasks; then
echo "Successfully open Tasks"
     end
  else
     echo "Cannot open Tasks"
     fail
  fi

}
fail() {
  echo "There were errors"
}

end() {
  echo "Work is finished"
}

if ./runcrud.sh start; then
   open_Safari
   open_Tasks
else
   fail
fi