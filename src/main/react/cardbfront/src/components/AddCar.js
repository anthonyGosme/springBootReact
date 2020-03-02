import React from "react";

import TextField from "@material-ui/core/TextField";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
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
      <Button style={{ margin: 10 }} variant="outlined" onClick={handleOpen}>
        new car
      </Button>

      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="form-dialog-title"
      >
        <DialogTitle>New Car</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            fullWidth
            label="Brand"
            name="brand"
            value={car.brand}
            onChange={handleChange}
          />
          <br />
          <TextField
            autoFocus
            fullWidth
            label="Model"
            name="model"
            value={car.model}
            onChange={handleChange}
          />
          <br />
          <TextField
            autoFocus
            fullWidth
            label="Color"
            name="color"
            value={car.color}
            onChange={handleChange}
          />
          <br />
          <TextField
            autoFocus
            fullWidth
            label="Year"
            name="year"
            value={car.year}
            onChange={handleChange}
          />
          <br />
          <TextField
            autoFocus
            fullWidth
            label="Price"
            name="price"
            value={car.price}
            onChange={handleChange}
          />
        </DialogContent>

        <DialogActions>
          <Button onClick={handleClose} color="secondary">
            cancel
          </Button>
          <Button onClick={saveCar} color="primary">
            add
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default AddCar;
