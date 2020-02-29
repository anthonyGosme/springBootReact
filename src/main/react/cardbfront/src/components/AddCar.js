import React from "react";

import TextField from "@material-ui/core/TextField";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";
import Button from "@material-ui/core/Button";
const AddCar = props => {
  const [open, setOpen] = React.useState(false);
  const [car, setCar] = React.useState({
    brand: "",
    model: "",
    color: "",
    year: "",
    fuel: "",
    price: ""
  });

  // open the modal form
  const handleOpen = () => {
    setOpen(true);
  };
  // Close the modal form
  const handleClose = () => {
    setOpen(false);
  };
  // get change
  const handleChange = e => {
    setCar({ ...car, [e.target.name]: e.target.value });
  };

  const saveCar = () => {
    props.addCar(car);
    handleClose();
  };

  return (
    <div>
      <button style={{ margin: 10 }} variant="outlined" onClick={handleOpen}>
        new car
      </button>

      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="form-dialog-title"
      >
        <DialogTitle>New Car</DialogTitle>
        <DialogContent>
          <input
            type="text"
            placeholder="Brand"
            name="brand"
            value={car.brand}
            onChange={handleChange}
          />
          <br />
          <input
            type="text"
            placeholder="Model"
            name="model"
            value={car.model}
            onChange={handleChange}
          />
          <br />
          <input
            type="text"
            placeholder="Color"
            name="color"
            value={car.color}
            onChange={handleChange}
          />
          <br />
          <input
            type="text"
            placeholder="Year"
            name="year"
            value={car.year}
            onChange={handleChange}
          />
          <br />
          <input
            type="text"
            placeholder="Price"
            name="price"
            value={car.price}
            onChange={handleChange}
          />
        </DialogContent>

        <DialogActions>
          <button onClick={handleClose} color="primary">
            cancel
          </button>
          <button onClick={saveCar} color="primary">
            add
          </button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default AddCar;
