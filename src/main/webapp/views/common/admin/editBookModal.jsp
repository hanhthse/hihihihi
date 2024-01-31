<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="editBookModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editBookModalLabel">Edit Book</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editBookForm" action="dashboard?action=edit" method="POST" enctype="multipart/form-data">
                    <!-- Booking ID (ẩn) -->
                    <input type="hidden" class="form-control" id="idEditInput" name="bookingId">

                    <!-- User Name -->
                    <div class="form-group">
                        <label for="userName">User Name:</label>
                        <input type="text" class="form-control" id="userNameEditInput" name="userName" readonly>
                    </div>

                    <!-- Pet Name -->
                    <div class="form-group">
                        <label for="petName">Pet Name:</label>
                        <input type="text" class="form-control" id="petNameEditInput" name="petName" readonly>
                    </div>

                    <!-- Cage ID -->
                    <div class="form-group">
                        <label for="cageId">Cage ID:</label>
                        <input type="text" class="form-control" id="cageIdEditInput" name="cageId">
                    </div>

                    <!-- Date Start -->
                    <div class="form-group">
                        <label for="startDate">Date Start:</label>
                        <input type="text" class="form-control" id="startDateEditInput" name="startDate">
                    </div>

                    <!-- Date End -->
                    <div class="form-group">
                        <label for="endDate">Date End:</label>
                        <input type="text" class="form-control" id="endDateEditInput" name="endDate">
                    </div>

                    <!-- Price -->
                    <div class="form-group">
                        <label for="totalCost">Price:</label>
                        <input type="text" class="form-control" id="totalCostEditInput" name="totalCost" readonly>
                    </div>

                    <!-- Status -->
                    <div class="form-group">
                        <label for="statusEditInput">Status:</label>
                        <select class="form-control" id="statusEditInput" name="status">
                            <option value="unconfirm">Unconfirm</option>
                            <option value="confirm">Confirm</option>
                            <option value="progress">Progress</option>
                            <option value="cancel">Cancel</option>
                            <option value="complete">Complete</option>
                        </select>
                    </div>

                    <!--Image-->
                    <div class="form-group">
                        <label for="image">Image: </label>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Upload</span>
                            </div>
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" id="imageEdit" name="image"
                                       onchange="displayImage2(this)">
                                <label class="custom-file-label">Choose file</label>
                            </div>
                        </div>
                        <img id="previewImage2" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="Preview"
                             style="display: none; max-width: 300px; max-height: 300px;">
                        <input type="hidden" id="currentImage" name="currentImage" value="">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-primary" form="editBookForm"
                        onclick="validateForm2()">Update</button>
            </div>
        </div>
    </div>
</div>

<script>
    function validateForm2() {
        let username = $('#userNameEditInput').val();
        let petname = $('#petNameEditInput').val();
        let cages = $('#cageIdEditInput').val();
        let dateStart = $('#startDateEditInput').val();
        let dateEnd = $('#endDateEditInput').val();
        let price = $('#totalCostEditInput').val();

        //xoá thông báo lỗi hiện tại
        $('.error').html('');

        if (username === '') {
            $('#nameEditError').html('Tên sách không được để trống');
        }

        if (petname === '') {
            $('#authorEditError').html('Tên tác giả không được để trống');
        }

        if (price === '') {
            $('#totalCostEditInput').html('Giá của bookings không được để trống');
        } else if (!$.isNumeric(price) || parseFloat(price) < 0) {
            $('#totalCostEditInput').html('Giá của quyển sách phải là số và không được nhỏ hơn 0');
        }

        if (quantity === '') {
            $('#quantityEditError').html('Số lượng sách không được để trống');
        } else if (!$.isNumeric(quantity) || parseInt(price) < 0) {
            $('#priceEditError').html('Số lượng của quyển sách phải là số và không được nhỏ hơn 0');
        }

        // Kiểm tra nếu không có lỗi thì submit form
        let error = '';
        $('.error').each(function () {
            error += $(this).html();
        });
        if (error === '') {
            $('#editBookForm').submit();
        } else {
            event.preventDefault();
        }
    }

    function displayImage2(input) {
        var previewImage = document.getElementById("previewImage2");
        var file = input.files[0];
        var reader = new FileReader();

        reader.onload = function (e) {
            previewImage.src = e.target.result;
            previewImage.style.display = "block";
        }

        reader.readAsDataURL(file);
    }

    function editBookModal(bookingId, userName, petName, cageId, startDate, endDate, totalCost, status, image) {
        $('#idEditInput').val(bookingId);
        $('#userNameEditInput').val(userName);
        $('#petNameEditInput').val(petName);
        $('#cageIdEditInput').val(cageId);
        $('#startDateEditInput').val(startDate);
        $('#endDateEditInput').val(endDate);
        $('#totalCostEditInput').val(totalCost);
        $('#statusEditInput').val(status);
        $('#currentImage').val(image);
        $('#previewImage2').attr('src', image).css('display', image ? 'block' : 'none');
    }



</script>
