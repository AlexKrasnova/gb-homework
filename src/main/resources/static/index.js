var app = angular.module("app", ['ui.bootstrap']);
app.controller("indexController", ['$scope','$modal', '$http', '$log',

    function ($scope, $modal, $http, $log) {

        const contextPath = 'http://localhost:8080';

        $scope.saveProduct = function () {
            $http.post(contextPath + '/products', $scope.NewProduct)
                .then(function (resp) {
                    $scope.fillTable()
                });
        };

        $scope.fillTable = function () {
            $http.get(contextPath + '/products')
                .then(function (resp) {
                    console.log(resp)
                    $scope.Products = resp.data
                })
        };

        $scope.removeProduct = function (id){
            $http.delete(contextPath + '/products/'+id)
                .then(function (resp) {
                    $scope.fillTable()
                })
        }

        /*$scope.updateProduct = function (id) {
            $scope.UpdatedProduct = {
                "id" : id,
                "code": "Глазированный сырок - Valio",
                "price": 100
            }
            $http.put(contextPath + '/products/' + id, $scope.UpdatedProduct)
                .then(function (resp) {
                    $scope.fillTable()
                })
        }*/

        $scope.fillTable()

        $scope.showForm = function (id) {
            $scope.message = "Show Form Button Clicked";
            //$scope.id = id;
            console.log($scope.message);

            var modalInstance = $modal.open({
                templateUrl: 'update-product-form.html',
                controller: ModalInstanceCtrl,
                scope: $scope,
                resolve: {
                    updateProductForm: function () {
                        return $scope.updateProductForm;
                    }/*,
                    productId: function () {
                        return id;
                    }*/
                }

            });

            modalInstance.result.then(function (selectedItem) {
                $scope.selected = selectedItem;
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };
        
    }]);

var ModalInstanceCtrl = function ($scope, $modalInstance, $http, updateProductForm) {
    $scope.form = {}
    //$scope.productId = productId;
    $scope.submitForm = function () {
        console.log('sdflk');
        console.log('В submitForm id = ' + $scope.id);
        console.log($scope.UpdatedProduct.name);

        $modalInstance.close('closed');
        const contextPath = 'http://localhost:8080';
        /*if ($scope.form.updateProductForm.$valid) {*/
/*            console.log('user form is in scope');
            console.log('sdfkfsl' + $scope.UpdatedProduct);
            $http.put(contextPath + '/products/' + $scope.id, $scope.UpdatedProduct)
                .then(function (resp) {
                    console.log($scope.UpdatedProduct)
                    $scope.fillTable()
                })
            $modalInstance.close('closed');*/
        /*} else {
            console.log('userform is not in scope');
        }*/
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};



