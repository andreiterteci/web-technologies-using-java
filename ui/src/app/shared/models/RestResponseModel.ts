export class RestResponseModel {
  constructor(public success: boolean, public entityId: string, public errors: ErrorModel[]) {}
}

export class ErrorModel {
  constructor(public message: string, public params: ErrorParameterModel[]) {}
}

export class ErrorParameterModel {
  constructor(public name: string, public value: string) {}
}
