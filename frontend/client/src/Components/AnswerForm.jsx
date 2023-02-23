const AnswerForm = ({onSave}) => {

    const handleSubmit = (event) => {
        event.preventDefault();
        const answer = event.target.form[0].value
        return onSave(answer)
    }


  return (
    <form action="" method="" class="form-example">
        <label>
          Enter your answer:
          <input type="text" name="answer" id="answer" required onSubmit={(event) => event.preventDefault()}/>
          <button type="button" onClick={(event) => handleSubmit(event)}>Send</button>
        </label>
    </form>
  );
};

export default AnswerForm;
