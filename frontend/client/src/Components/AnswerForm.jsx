const AnswerForm = ({onSave}) => {

    const handleSubmit = (event) => {
        event.preventDefault();
        const answer = event.target.form[0].value
        onSave(answer)
    }


  return (
    <form action="" method="Post" class="form-example">
        <label>
          Enter your answer:
          <input type="text" name="answer" id="answer" required />
          <button type="button" onClick={(event) => handleSubmit(event)}>Send</button>
        </label>
    </form>
  );
};

export default AnswerForm;
